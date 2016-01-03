package com.xingfugo.business.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.xingfugo.business.module.common.NameValuePair;

/**
 * 购物车商品条目，以商品ID+商品参数作为唯一标识
 */
public class CartItem {
	private static final long serialVersionUID = 6373633224357829660L;
	
	//购物车商品
	private Goods goods = new Goods();
	//商品数量
	private int amount = 0;
	//商品所属商家信息，只包含商家ID和商家名称
	private Seller seller = new Seller();

	//TODO 商品参数（商品不同，参数名称和个数不同，允许多个参数），参数名称和参数值
	//private List<NameValuePair> goodsParamList = null;
	
	//商品参数字符串，格式：参数1key=参数1值;参数2key=参数2值;...
	private String goods_param = null;
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Goods getGoods() {
		return goods;
	}
	
	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getGoods_param() {
		return goods_param;
	}

	public void setGoods_param(String goodsParam) {
		this.goods_param = orderGoodsParams(goodsParam);
	}
	
	public void setGoods_paramBySplit(String goodsParam,String splitStr) {
		this.goods_param = orderGoodsParamsBySplitStr(goodsParam,splitStr);
	}
		
		
	public static String orderGoodsParams(String goodsParams){
		return orderGoodsParamsBySplitStr(goodsParams,"\\.");
	}
	
	
	/**
	 * 商品参数排序处理，保证参数项不因顺序不同而产生不同的购物车条目
	 * @param goodsParams 商品参数字符串，格式：参数1key=参数1值;参数2key=参数2值;...
	 * @return 排序处理后的商品参数字符串
	 */
	public static String orderGoodsParamsBySplitStr(String goodsParams,String splitStr){
		if(StringUtils.isEmpty(goodsParams)){
			return null;
		}
		
		String[] param = null;
		String[] params = goodsParams.split(";");
		int size = params.length;
		List<NameValuePair> pairList = new ArrayList<NameValuePair>(size);
		for(int i = 0; i < size; i++){
			param = params[i].split(splitStr);
			
			NameValuePair pair = new NameValuePair();
			
			//参数名称
			if(param.length > 0){
				pair.setName(param[0]);
			}
			
			//参数值
			if(param.length > 1){
				pair.setValue(param[1]);
			}
			
			pairList.add(pair);
		}
		
		Collections.sort(pairList);
		
		StringBuffer buf = new StringBuffer();
		for(NameValuePair pair : pairList){
			buf.append(pair.getName());
			buf.append(".");
			buf.append(pair.getValue());
			buf.append(";");
		}
		
		if(buf.length() > 0){
			buf.deleteCharAt(buf.length() - 1);
		}
		
		return buf.toString();
	}
	
	/**
	 * 转换购物车条目字符串为购物车条目列表
	 * @param cartItemString 购物车条目字符串
	 *	格式：商品1ID:商品1参数1=参数值;商品1参数2=参数值;...:商品1数量,
	 *	      商品2ID:商品2参数1=参数值;商品2参数2=参数值;...:商品2数量,... 
	 * @return
	 */
	public static List<CartItem> parseCartItemString(String cartItemString){
		if(StringUtils.isEmpty(cartItemString)){
			return Collections.emptyList();
		}
		
		//参数解析
		String[] strArray = null;
		int goods_id = 0;
		int amount = 0;
		int goodsSpecLen = 0;
		
		String[] cartItemEntries = cartItemString.split(",");
		int size = cartItemEntries.length;
		List<CartItem> cartItemList = new ArrayList<CartItem>(size);
		for(int i = 0; i < size; i++){
			CartItem cartItem = new CartItem();

			strArray = cartItemEntries[i].split(":");
			goodsSpecLen = strArray.length;

			//第一个参数项，商品ID
			if(goodsSpecLen > 0){
				goods_id = Integer.parseInt(strArray[0]);
				cartItem.getGoods().setGoods_id(goods_id);
			}
			
			//3个参数项，包含商品参数
			if(goodsSpecLen > 2){
				cartItem.setGoods_param(strArray[1]);
				amount = Integer.parseInt(strArray[2]);
				cartItem.setAmount(amount);
			}
			//2个参数项，无商品参数
			else{
				amount = Integer.parseInt(strArray[1]);
				cartItem.setAmount(amount);
			}
			
			cartItemList.add(cartItem);
		}
		
		return cartItemList;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CartItem) {
			CartItem cartItem = (CartItem) obj;
			if(!goods.equals(cartItem.getGoods())){
				return false;
			}
			
			if(StringUtils.isEmpty(goods_param)){
				return StringUtils.isEmpty(cartItem.getGoods_param()) ? true : false;
			}
			else{
				return getGoods_param().equals(cartItem.getGoods_param());
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		StringBuffer buf = new StringBuffer();
		buf.append(goods.getGoods_id());
		if(StringUtils.isNotEmpty(goods_param)){
			buf.append(":");
			buf.append(getGoods_param());
		}

		return buf.toString().hashCode();
	}
	
	public int getHash_code() {
		return hashCode();
	}
}
