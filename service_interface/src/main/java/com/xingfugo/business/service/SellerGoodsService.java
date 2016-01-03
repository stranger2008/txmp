package com.xingfugo.business.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingfugo.business.dao.GoodsDao;
import com.xingfugo.business.dao.GoodsStockHistoryMapper;
import com.xingfugo.business.dao.SellerGoodsMapper;
import com.xingfugo.business.module.CategoryAttrValue;
import com.xingfugo.business.module.GoodsAttr;
import com.xingfugo.business.module.GoodsStockHistory;
import com.xingfugo.business.module.InfoAttr;
import com.xingfugo.business.module.SellerGoodsWithBLOBs;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.business.module.query.GoodsStockHistoryQueryForm;
import com.xingfugo.business.module.query.SellerGoodsQueryForm;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.util.PageResultBuilder;
import com.xingfugo.util.RandomStrUtil;

@Service
public class SellerGoodsService {
	
	//0：未审核 1：审核通过 2：审核不通过 3：禁用
	private static final String GOODS_STATUS_UNAUDIT = "0";
	
	//分类属性类型,默认单行文本框
	private static final String CATEGORY_ATTR_TYPE_TEXT = "0";
	//多行文本框
	private static final String CATEGORY_ATTR_TYPE_TEXTAREA = "1";
	//单选按钮
	private static final String CATEGORY_ATTR_TYPE_RADIO = "2";
	//复选框
	private static final String CATEGORY_ATTR_TYPE_CHECKBOX = "3";

	@Autowired
	private SellerGoodsMapper mapper;
	
	@Autowired
	private GoodsStockService goodsStockService;
	
	@Autowired
	private InfoAttrService infoAttrService;
	
	@Autowired
	private GoodsStockHistoryMapper goodsStockHistoryMapper;
	
	@Autowired
	private GoodsDao goodsDao;
	
	public PageResult getGoodsListByPage(SellerGoodsQueryForm goodsQueryForm){
		String sortCode = goodsQueryForm.getPg().getSortCode();
		goodsQueryForm.getPg().setSortField("g.in_date desc");
		
//		goodsQueryForm.setIs_del("0");
		List<Map<String, Object>> list = goodsDao.getGoodsListByPage(goodsQueryForm);
		PageResult result = PageResultBuilder.build(goodsQueryForm.getPg(), list);
		ImgPathUitls.filterImagePath_spec((List<Map<String, Object>>)result.getList(),70,70);
		return result;
	}
	
	public void del(Integer goodsId){
		mapper.deleteByPrimaryKey(goodsId);
	}
	
	public void batchDel(List<Integer> goodsIdList){
		mapper.batchDelete(goodsIdList);
	}
	
	@Transactional
	public void insert(SellerGoodsWithBLOBs sellerGoods){
		
		String infoattrId = RandomStrUtil.getNumberRand();
		sellerGoods.setInfoattrId(infoattrId);

		if("0".equals(sellerGoods.getIsShip())){
			sellerGoods.setShipPrice("0.00");
		}
		//设置为未审核状态
		sellerGoods.setInfoState(GOODS_STATUS_UNAUDIT);
		//商品信息
		mapper.insert(sellerGoods);
		
		List<GoodsAttr> goodsAttrList = sellerGoods.getGoodsAttrList();
		List<InfoAttr> infoAttrList = goodsAttrList2InfoAttrList(goodsAttrList, infoattrId);
		
		//商品属性值
		infoAttrService.batchInsert(infoAttrList);
		
		//库存变化记录
		if(sellerGoods.getNowStock() > 0){
			GoodsStockHistory goodsStockHistory = new GoodsStockHistory();
			goodsStockHistory.setGoodsId(sellerGoods.getGoodsId());
			goodsStockHistory.setBeforeChange(0);
			goodsStockHistory.setAfterChange(sellerGoods.getNowStock());
			goodsStockHistory.setChangeAmount(sellerGoods.getNowStock());
			goodsStockHistory.setChangeDesc("初始化商品库存");
			goodsStockHistory.setChangeReason((short)2);
			goodsStockHistory.setChangeType(true);
			
			goodsStockService.save(goodsStockHistory);
		}
	}
	
	public SellerGoodsWithBLOBs detail(Integer goodsId){
//		List<InfoAttr> infoAttrList = infoAttrService.selectByGoodsId(goodsId);
//		List<GoodsAttr> goodsAttrList = new ArrayList<GoodsAttr>(infoAttrList == null ? 0 : infoAttrList.size());
//		if(infoAttrList != null){
//			for(InfoAttr infoAttr : infoAttrList){
//				GoodsAttr goodsAttr = new GoodsAttr();
//				
//				goodsAttr.setAttrId(infoAttr.getAttrId());
//				goodsAttr.setAttrName(infoAttr.getAttrName());
//				goodsAttr.setSortNo(infoAttr.getSortNo());
//				
//				List<CategoryAttrValue> attrValueList = goodsAttr.getAttrValueList();
//				CategoryAttrValue categoryAttrValue = new CategoryAttrValue();
//				categoryAttrValue.setAttrValue(infoAttr.getAttrValue());
//				categoryAttrValue.setTradeId(infoAttr.getValueId());
//				attrValueList.add(categoryAttrValue);
//				
//				goodsAttrList.add(goodsAttr);
//			}
//		}
		
		SellerGoodsWithBLOBs sellerGoodsWithBLOBs = mapper.selectByPrimaryKey(goodsId);
		String imgPath = ImgPathUitls.filterImagePath(sellerGoodsWithBLOBs.getImgPath());
		sellerGoodsWithBLOBs.setImgPath(imgPath);
//		sellerGoodsWithBLOBs.setGoodsAttrList(goodsAttrList);
		//原始数据此内容可能为空,造成验证不能通过
		if(StringUtils.isBlank(sellerGoodsWithBLOBs.getShipPrice())){
			sellerGoodsWithBLOBs.setShipPrice("0.00");
		}
		return sellerGoodsWithBLOBs;
	}
	
	public SellerGoodsWithBLOBs detailBaseInfo(Integer goodsId){
		SellerGoodsWithBLOBs sellerGoodsWithBLOBs = mapper.selectByPrimaryKey(goodsId);
		return sellerGoodsWithBLOBs;
	}
	
	@Transactional
	public void update(SellerGoodsWithBLOBs record){
		//删除原来的infoattr记录
		infoAttrService.deletByGoodsId(record.getGoodsId());
		
		//插入新的infoattr记录
		List<InfoAttr> infoAttrList = goodsAttrList2InfoAttrList(record.getGoodsAttrList(), record.getInfoattrId());
		infoAttrService.batchInsert(infoAttrList);

		if("0".equals(record.getIsShip())){
			record.setShipPrice("0.00");
		}
		
		//设置为未审核状态
		record.setInfoState(GOODS_STATUS_UNAUDIT);
		//更新商品信息
		mapper.updateBaseInfoByPrimaryKeyWithBLOBs(record);
	}
	
	/**
	 * 更新商品库存
	 * @param goods_id 商品id
	 * @param amount 商品变动数量
	 * @param changeType 商品变动类型(true: 增加; false: 减少)
	 * @param changeReason 变动原因(0，订单创建；1：订单取消；2：卖家新增；3：其他；)
	 * @param changeDesc 变动原因描述
	 * @陈显革
	 */
	public void updateGoodsStock(Integer goods_id, int amount, boolean changeType, int changeReason, String changeDesc) {
		
		SellerGoodsWithBLOBs sellerGoods = mapper.selectByPrimaryKey(goods_id);
		int oldAmount = sellerGoods.getNowStock();
		
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("goodsId", goods_id);
		param.put("amount", changeType ? amount : (-1 * amount));
		mapper.updateStockByPrimaryKey(param);
		
		//增加商品库存变化记录
		GoodsStockHistory goodsStockHistory = new GoodsStockHistory();
		goodsStockHistory.setGoodsId(goods_id);
		goodsStockHistory.setBeforeChange(sellerGoods.getNowStock());
		goodsStockHistory.setAfterChange(changeType ? oldAmount + amount : sellerGoods.getNowStock() - amount);
		goodsStockHistory.setChangeAmount(amount);
		goodsStockHistory.setChangeDesc(changeDesc);
		goodsStockHistory.setChangeReason((short)changeReason);
		goodsStockHistory.setChangeType(changeType);
		
		goodsStockService.save(goodsStockHistory);
	}
	
	@Transactional
	public void addStock(GoodsStockHistory goodsStock, SellerGoodsWithBLOBs sellerGoods){
		if(goodsStock == null || goodsStock.getChangeAmount() == 0){
			return;
		}
		
		Integer goodsId = goodsStock.getGoodsId();
		Integer amount = goodsStock.getChangeAmount();
		Boolean isAdd = goodsStock.getChangeType();
		
		//修改商品库存信息
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("goodsId", goodsId);
		param.put("amount", isAdd ? amount : (-1 * amount));
		mapper.updateStockByPrimaryKey(param);
		
		//增加商品库存变化记录
		GoodsStockHistory goodsStockHistory = new GoodsStockHistory();
		goodsStockHistory.setGoodsId(goodsId);
		goodsStockHistory.setBeforeChange(sellerGoods.getNowStock());
		goodsStockHistory.setAfterChange(isAdd ? sellerGoods.getNowStock()+amount : sellerGoods.getNowStock()-amount);
		goodsStockHistory.setChangeAmount(amount);
		String changeDesc = null;
		if(isAdd) {
			changeDesc = "商家手动增加库存";
		} else {
			changeDesc = "商家手动减少库存";
		}
		goodsStockHistory.setChangeDesc(changeDesc);
		goodsStockHistory.setChangeReason((short)3);
		goodsStockHistory.setChangeType(isAdd);
		
		goodsStockService.save(goodsStockHistory);
	}
	
	public PageResult stockHistoryPageList(GoodsStockHistoryQueryForm queryForm){
		List<GoodsStockHistory> list = goodsStockHistoryMapper.selectByPage(queryForm);
		PageResult result = PageResultBuilder.build(queryForm.getPg(), list);
		return result;
	}
	
	public void changeSaleState(Integer goodsId, boolean isOnSale){
		SellerGoodsWithBLOBs sellerGoods = mapper.selectByPrimaryKey(goodsId);
		Date now = new Date();
		Date downDate = sellerGoods.getDownDate();
		if(isOnSale){
			if(downDate.after(now)){
				return;
			}

			Calendar cal = Calendar.getInstance();
			cal.setTime(now);
			cal.add(Calendar.YEAR, 1);
			Date newDownDate = cal.getTime();
			
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("goodsId", goodsId);
			param.put("downDate", newDownDate);
			mapper.updateDownDateByPrimaryKey(param);
			return;
		}
		
		if(downDate.before(now)){
			return;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DATE, -1);
		Date newDownDate = cal.getTime();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("goodsId", goodsId);
		param.put("downDate", newDownDate);
		mapper.updateDownDateByPrimaryKey(param);
	}
	
	private List<InfoAttr> goodsAttrList2InfoAttrList(List<GoodsAttr> goodsAttrList, String infoattrId){
		if(goodsAttrList == null){
			return Collections.emptyList();
		}
		
		List<InfoAttr> infoAttrList = new ArrayList<InfoAttr>(goodsAttrList.size());
		for(GoodsAttr goodsAttr : goodsAttrList){
			InfoAttr infoAttr = new InfoAttr();
			infoAttr.setInfoattrId(infoattrId);
			infoAttr.setAttrId(goodsAttr.getAttrId());
			infoAttr.setAttrName(goodsAttr.getAttrName());
			infoAttr.setSortNo(goodsAttr.getSortNo());
			
			List<CategoryAttrValue> attrValueList = goodsAttr.getAttrValueList();
			if(attrValueList != null && !attrValueList.isEmpty()){
				//单行文本
				if(CATEGORY_ATTR_TYPE_TEXT.equals(goodsAttr.getAttrType())){
					infoAttr.setAttrValue(attrValueList.get(0).getAttrValue());
					//单行文本框value_id为空
//					infoAttr.setValueId(attrValueList.get(0).getTradeId() == null ? "/" : attrValueList.get(0).getTradeId() + "/");
				}
				//单选钮
				else if(CATEGORY_ATTR_TYPE_RADIO.equals(goodsAttr.getAttrType())){
					String valueId = attrValueList.get(0).getTradeId();
					if(valueId != null){
						valueId += "/";
					}
					//单选按钮attr_value为空
//					infoAttr.setAttrValue(valueId);
					infoAttr.setValueId(valueId);
				}
				//复选框
				else if(CATEGORY_ATTR_TYPE_CHECKBOX.equals(goodsAttr.getAttrType())){
					String valueId = attrValueList.get(0).getTradeId();
					if(valueId != null){
						valueId = valueId.replaceAll(",", "/");
						valueId += "/";
					}
					//复选框attr_value为空
//					infoAttr.setAttrValue(valueId);
					infoAttr.setValueId(valueId);
				}
			}
			
			infoAttrList.add(infoAttr);
		}
		
		return infoAttrList;
	}
	
	/**
	 * 取得各种状态商品的数量
	 * @return
	 * @author 陈显革
	 * @date 2014-09-18
	 */
	public List<Map<String, Object>> getGoodsStatusCounts(GoodsQueryForm goodsQueryForm) {
		return goodsDao.getGoodsStatusCounts(goodsQueryForm);
	}

	public void returnByPrimaryKey(Integer goodsId) {
		mapper.returnByPrimaryKey(goodsId);
		
	}

	public void deletegoodsByPrimaryKey(Integer goodsId) {
		mapper.deletegoodsByPrimaryKey(goodsId);
		
	}

	public void batchreturn(List<Integer> delGoodsIdList) {
		mapper.batchreturn(delGoodsIdList);
		
	}

}
