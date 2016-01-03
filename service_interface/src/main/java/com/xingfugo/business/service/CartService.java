package com.xingfugo.business.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.lean.redis.JedisService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.xingfugo.business.module.CartItem;
import com.xingfugo.business.module.UserCart;

public abstract class CartService {
	private static final String FIELD_CUST_SHOP_NAME = "shop_name";
	private static final String FIELD_CUST_ID = "cust_id";
	private static final String FIELD_GOODS_ATTR = "goods_attr";
	
	private JedisService jedisService = new JedisService();
	
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 获取用户购物车
	 * @param user_id 用户ID
	 * @return 购物车
	 * 
	 */
	public UserCart getUserCart(String user_id){
		String redisUserCartKey = redisUserCartKey(user_id);
		List<String> cartItemList = jedisService.LISTS.lrange(redisUserCartKey, 0, -1);
		return redisList2UserCart(user_id, cartItemList);
	}
	
	public CartItem getCartItem(String user_id, int cartItemHashCode){
		UserCart userCart = getUserCart(user_id);
		return userCart.getCartItem(cartItemHashCode);
	}
	
	public List<CartItem> getCartItem(String user_id, List<Integer> cartItemHashCodeList){
		UserCart userCart = getUserCart(user_id);
		return userCart.getCartItem(cartItemHashCodeList);
	}
	
	/**
	 * 向用户购物车内添加商品
	 * @param user_id 用户ID
	 * @param goods_id 商品ID
	 * @param goodsParam 商品参数字符串，格式：参数1key:参数1值,参数2key:参数2值,...
	 * @param amount 商品数据量
	 * @return 购物车商品条目（添加后商品及数量），商品不存在时返回空
	 */
	public CartItem add(String user_id, Integer goods_id, String goodsParam, int amount){
		Map<String, Object> map = goodsService.getGoodsDetailWithCustById(goods_id);
		//商品不存在
		if(map == null){
			return null;
		}
		
		CartItem cartItem = createCartItemByGoodsMap(map);
		cartItem.setAmount(amount);
		cartItem.setGoods_param(goodsParam);
		
		String redisUserCartKey = redisUserCartKey(user_id);
		
		synchronized (this) {
			UserCart userCart = getUserCart(user_id);
			boolean haveCartItem = userCart.exist(cartItem);
			
			cartItem = userCart.add(cartItem);
			
			if(haveCartItem){
				int index = userCart.getIndex(cartItem);
				jedisService.LISTS.lset(redisUserCartKey, index, cartItem2RedisValue(cartItem));
			}
			else{
				jedisService.LISTS.rpush(redisUserCartKey, cartItem2RedisValue(cartItem));
			}
		}

		setExpiredSeconds(redisUserCartKey);
		
		return cartItem;
	}
	
	/**
	 * 从用户购物车中删除指定商品
	 * @param user_id 用户ID
	 * @param goods_id 商品ID
	 * @param goodsParam 商品参数字符串，格式：参数1key:参数1值,参数2key:参数2值,...
	 * @return  购物车商品条目（删除前商品及数量），为空时表示商品不在购物车内
	 */
	public CartItem remove(String user_id, Integer goods_id, String goodsParam){
		UserCart userCart = getUserCart(user_id);
		
		CartItem cartItem = new CartItem();
		cartItem.getGoods().setGoods_id(goods_id);
		cartItem.setGoods_param(goodsParam);
		
		cartItem = userCart.remove(cartItem);
		if(cartItem == null){
			return null;
		}
		
		String redisUserCartKey = redisUserCartKey(user_id);
		jedisService.LISTS.lrem(redisUserCartKey, 0, cartItem2RedisValue(cartItem));
		
		setExpiredSeconds(redisUserCartKey);
		
		return cartItem;
	}
	
	public CartItem remove(String user_id, int cartItemHashCode){
		UserCart userCart = getUserCart(user_id);

		CartItem cartItem = userCart.remove(cartItemHashCode);
		if(cartItem == null){
			return null;
		}
		
		String redisUserCartKey = redisUserCartKey(user_id);
		jedisService.LISTS.lrem(redisUserCartKey, 0, cartItem2RedisValue(cartItem));
		
		setExpiredSeconds(redisUserCartKey);

		return cartItem;
	}
	
	public List<CartItem> removeAll(String user_id, List<Integer> cartItemHashCodeList){
		UserCart userCart = getUserCart(user_id);
		int cartSize = userCart.size();
		
		List<CartItem> cartItemList = userCart.removeAll(cartItemHashCodeList);
		if(CollectionUtils.isEmpty(cartItemList)){
			return null;
		}
		
		String redisUserCartKey = redisUserCartKey(user_id);
		if(cartSize == cartItemList.size()){
			jedisService.KEYS.del(redisUserCartKey);
			return cartItemList;
		}
		
		for(CartItem cartItem : cartItemList){
			jedisService.LISTS.lrem(redisUserCartKey, 0, cartItem2RedisValue(cartItem));
		}
		
		setExpiredSeconds(redisUserCartKey);
		
		return cartItemList;
	}

	/**
	 * 批量删除购物车中商品
	 * @param user_id 用户ID
	 * @param goodsMapList 商品列表：键为goods_id，标识商品ID，整数；键为goods_param，标识商品参数，字符型
	 * @return 购物车商品条目列表（删除前商品及数量）
	 */
	public List<CartItem> remove(String user_id, List<Map<String, Object>> goodsMapList){
		UserCart userCart = getUserCart(user_id);
		
		List<CartItem> cartItemList = createCartItemList(goodsMapList);
		cartItemList = userCart.remove(cartItemList);

		String redisUserCartKey = redisUserCartKey(user_id);
		for(CartItem cartItem : cartItemList){
			jedisService.LISTS.lrem(redisUserCartKey, 0, cartItem2RedisValue(cartItem));
		}
		
		setExpiredSeconds(redisUserCartKey);
		
		return cartItemList;
	}

	/**
	 * 购物车整体保存
	 * @param user_id 用户ID
	 * @param cartItemList 购物车商品条目列表
	 * @return 返回保存后的用户购物车信息
	 */
	public UserCart save(String user_id, List<CartItem> cartItemList){
		UserCart userCart = getUserCart(user_id);
		
		//返回空购物车信息
		if(CollectionUtils.isEmpty(cartItemList)){
			return userCart;
		}
		
		String redisUserCartKey = redisUserCartKey(user_id);
		//用户购物车不存在，全部新增
		if(userCart.getCartItems().isEmpty()){
			for(CartItem cartItem : cartItemList){
				userCart.add(cartItem);
				//redis新增列表项
				jedisService.LISTS.rpush(redisUserCartKey, cartItem2RedisValue(cartItem));
			}
		}
		//用户购物车已存在，需要合并
		else{
			for(CartItem newCartItem : cartItemList){
				//商品已存在，在原来数量上增加
				if(userCart.exist(newCartItem)){
					CartItem oldCartItem = userCart.find(newCartItem);
					oldCartItem.setAmount(oldCartItem.getAmount() + newCartItem.getAmount());
					CartItem modifyCartItem = userCart.modify(oldCartItem);
					//redis修改原来列表项
					jedisService.LISTS.lset(redisUserCartKey, userCart.getIndex(oldCartItem),
							cartItem2RedisValue(modifyCartItem));
				}
				else{
					userCart.add(newCartItem);
					//redis新增列表项
					jedisService.LISTS.rpush(redisUserCartKey, cartItem2RedisValue(newCartItem));
				}
			}
		}
		
		setExpiredSeconds(redisUserCartKey);

		userCart = getUserCart(user_id);
		return userCart;
	}
	
	/**
	 * 清空用户购物车
	 * @param user_id 用户ID
	 * @return 清空操作是否成功
	 */
	public boolean empty(String user_id){
		UserCart userCart = getUserCart(user_id);
		if(userCart.isEmpty()){
			return true;
		}
		
		jedisService.KEYS.del(redisUserCartKey(user_id));
		
		return true;
	}
	
	/**
	 * 修改用户购物车内商品数据量
	 * @param user_id 用户ID
	 * @param goods_id 商品ID
	 * @param goodsParam 商品参数字符串，格式：参数1key:参数1值,参数2key:参数2值,...
	 * @param amount 商品数量
	 * @return 购物车商品条目（修改后商品及数量），商品不存在时返回空
	 */
	public CartItem modify(String user_id, Integer goods_id, String goodsParam, int amount){
		Map<String, Object> map = goodsService.getGoodsDetailWithCustById(goods_id);
		//商品不存在
		if(map == null){
			return null;
		}
		
		CartItem cartItem = createCartItemByGoodsMap(map);
		cartItem.setGoods_param(goodsParam);
		cartItem.setAmount(amount);
		
		String redisUserCartKey = redisUserCartKey(user_id);
		synchronized (this) {
			UserCart userCart = getUserCart(user_id);
			boolean haveCartItem = userCart.exist(cartItem);
			cartItem = userCart.modify(cartItem);
			
			if(haveCartItem){
				int index = userCart.getIndex(cartItem);
				jedisService.LISTS.lset(redisUserCartKey, index, cartItem2RedisValue(cartItem));
			}
			else{
				jedisService.LISTS.rpush(redisUserCartKey, cartItem2RedisValue(cartItem));
			}
		}
	
		setExpiredSeconds(redisUserCartKey);
		
		return cartItem;
	}
	
	public CartItem modify(String user_id, int cartItemHashCode, int amount){
		UserCart userCart = getUserCart(user_id);
		CartItem cartItem = userCart.getCartItem(cartItemHashCode);
		if(cartItem == null){
			return null;
		}
		
		cartItem.setAmount(amount);
		cartItem = userCart.modify(cartItem);
		
		int cartItemIndex = userCart.getIndex(cartItem);
		
		String redisUserCartKey = redisUserCartKey(user_id);
		jedisService.LISTS.lset(redisUserCartKey, cartItemIndex, cartItem2RedisValue(cartItem));
		
		setExpiredSeconds(redisUserCartKey);
		
		return cartItem;
	}
	
	/**
	 * 根据用户ID（标识），生成购物车存储在redis库中的唯一键值
	 * @param user_id 用户ID（标识）
	 * @return 用户购物车唯一键值
	 */
	abstract public String redisUserCartKey(String user_id);
	
	/**
	 * 返回购物车的过期时间，以秒为单位，小于等于0时表示永不过期
	 * @return 过期秒数
	 */
	abstract public int redisExpiredSeconds();
	
	//设置过期时间
	private void setExpiredSeconds(String redisKey){
		int seconds = redisExpiredSeconds();
		if(seconds > 0){
			jedisService.KEYS.expired(redisKey, seconds);
		}
	}
	
	protected UserCart redisList2UserCart(String user_id, List<String> redisCartItemList){
		UserCart userCart = new UserCart(user_id);
		if(CollectionUtils.isEmpty(redisCartItemList)){
			return userCart;
		}
		
		int size = redisCartItemList.size();
		List<CartItem> cartItems = new ArrayList<CartItem>(size);
		List<Integer> goodsIds = new ArrayList<Integer>(size);
		for(String redisCartItem : redisCartItemList){

			String[] s = redisCartItem.split(":");
			int goodsId = Integer.parseInt(s[0]);
			
			if(!goodsIds.contains(goodsId)){
				goodsIds.add(goodsId);
			}
			
			CartItem cartItem = new CartItem();
			cartItem.getGoods().setGoods_id(goodsId);
			//3个参数项，包含商品参数
			if(s.length > 2){
				cartItem.setGoods_param(s[1]);
				cartItem.setAmount(Integer.parseInt(s[2]));
			}
			//2个参数项，无商品参数
			else{
				cartItem.setAmount(Integer.parseInt(s[1]));
			}
			
			cartItems.add(cartItem);
		}
		
		copyGoodsInfo2CartItem(goodsIds, cartItems);
		userCart.setCartItems(cartItems);
		return userCart;
	}
	
	protected String cartItem2RedisValue(CartItem cartItem){
		StringBuffer buf = new StringBuffer();
		buf.append(cartItem.getGoods().getGoods_id()).append(":");
		if(StringUtils.isNotEmpty(cartItem.getGoods_param())){
			buf.append(cartItem.getGoods_param()).append(":");
		}
		buf.append(cartItem.getAmount());
		
		return buf.toString();
	}
	
	public static CartItem createCartItemByGoodsMap(Map<String, Object> goodsMap){
		CartItem cartItem = new CartItem();
		//获取商品参数信息
		String goodsParam = (String)goodsMap.get(FIELD_GOODS_ATTR);
		cartItem.setGoods_param((goodsParam == null) ? null : goodsParam);

		Integer sellerId = (Integer)goodsMap.get(FIELD_CUST_ID);
		String sellerName = (String)goodsMap.get(FIELD_CUST_SHOP_NAME);
		cartItem.getSeller().setId(sellerId);
		cartItem.getSeller().setName(sellerName);
		
		try {
			BeanUtils.populate(cartItem.getGoods(), goodsMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return cartItem;
	}
	
	private void copyGoodsInfo2CartItem(List<Integer> goodsIds, List<CartItem> cartItems){
		if(CollectionUtils.isEmpty(cartItems)){
			return;
		}
		
		List<Map<String, Object>> goodsMapList = goodsService.getGoodsDetailListWithCustByIds(goodsIds);
		if(CollectionUtils.isEmpty(goodsMapList)){
			return;
		}
		
		List<CartItem> cartItemList = new ArrayList<CartItem>(goodsMapList.size());
		for(Map<String, Object> goodsMap : goodsMapList){
			CartItem cartItem = createCartItemByGoodsMap(goodsMap);
			cartItemList.add(cartItem);
		}
		
		int size = cartItemList.size();
		CartItem goodsCartItem = null;
		for(CartItem cartItem : cartItems){
			int goods_id = cartItem.getGoods().getGoods_id();
			
			for(int i = 0; i < size; i++){
				CartItem tempCartItem = cartItemList.get(i);
				if(goods_id == tempCartItem.getGoods().getGoods_id()){
					goodsCartItem = tempCartItem;
					break;
				}
			}
			
			if(goodsCartItem == null){
				continue;
			}
			
			try {
				BeanUtils.copyProperties(cartItem.getGoods(), goodsCartItem.getGoods());
				BeanUtils.copyProperties(cartItem.getSeller(), goodsCartItem.getSeller());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			goodsCartItem = null;
		}
	}
	
	private List<CartItem> createCartItemList(List<Map<String, Object>> goodsMapList){
		if(CollectionUtils.isEmpty(goodsMapList)){
			return Collections.emptyList();
		}
		
		List<CartItem> cartItemList = new ArrayList<CartItem>(goodsMapList.size());
		for(Map<String, Object> goodsMap : goodsMapList){
			CartItem cartItem = new CartItem();
			cartItem.getGoods().setGoods_id((Integer)goodsMap.get("goods_id"));
			cartItem.setGoods_param((String)goodsMap.get("goods_param"));
			cartItemList.add(cartItem);
		}
		
		return cartItemList;
	}
}
