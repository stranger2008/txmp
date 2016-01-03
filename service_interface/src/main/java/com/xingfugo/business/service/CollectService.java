package com.xingfugo.business.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.CollectDao;
import com.xingfugo.business.dao.GoodsDao;
import com.xingfugo.business.module.Collect;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.CollectQueryForm;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.util.PageResultBuilder;


@Service
public class CollectService{
	
	//会员中心商品收藏右侧商品显示的数量
	private static final Integer COLLCET_LIST_GOODS_SIZE = 5;

	@Autowired
	private CollectDao collectDao;
	
	@Autowired
	private GoodsDao goodsDao;
	
	public Integer insertCollect(Collect collect){
		collectDao.insertCollect(collect);
		return collect.getColl_id();
	}
	
	public void batchInsertCollect(List<Collect> collectList){
		collectDao.batchInsertCollect(collectList);
	}
	
	public void deleteCollect(Integer id){
		collectDao.deleteCollect(id);
	}
	
	public void batchDeleteCollect(List<Integer> ids){
		collectDao.batchDeleteCollect(ids);
	}
	
	public PageResult getCollectGoodsListByPage(CollectQueryForm collectQueryForm){
		List list = collectDao.getCollectGoodsListByPage(collectQueryForm);
		//商品图片url处理
		ImgPathUitls.filterImagePath_spec(list,200,200);
		PageResult result = PageResultBuilder.build(collectQueryForm.getPg(), list);
		return result;
	}
	
	public PageResult getCollectShopListByPage(CollectQueryForm collectQueryForm){
		List list = collectDao.getCollectShopListByPage(collectQueryForm);
		//找出对应的商家的商品信息加入到list中
		setShoplistInGoodsListByCustId(list);
		ImgPathUitls.filterImagePath(list, "shop_logo");
		PageResult result = PageResultBuilder.build(collectQueryForm.getPg(), list);
		return result;
	}
	
	public void setShoplistInGoodsListByCustId(List list){
		if(list == null){
			return;
		}
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			Map shopMap = (Map)itr.next();
			Integer cust_id = -1;
			if(shopMap.get("cust_id")!=null){
				cust_id = Integer.parseInt( shopMap.get("cust_id").toString() );
				GoodsQueryForm goodsQueryForm = new GoodsQueryForm();
				goodsQueryForm.setCust_id(cust_id);
				goodsQueryForm.setIs_onsell(true);
				goodsQueryForm.getPg().setSize(COLLCET_LIST_GOODS_SIZE);
				List goodsList = this.goodsDao.getGoodsListByPage(goodsQueryForm);
				ImgPathUitls.filterImagePath(goodsList, "img_path");
				shopMap.put("goodsList", goodsList);
			}
		}
	}
	
	public List getGoodsListByCustId(List goodsList,String cust_id){
		if(goodsList == null){
			return null;
		}
		List retList = new ArrayList();
		Iterator _itr = goodsList.iterator();
		while(_itr.hasNext()){
			Map shopMap = (Map)_itr.next();
			String _cust_id = "";
			if(shopMap.get("cust_id")!=null){
				_cust_id = shopMap.get("cust_id").toString();
				if(cust_id.equals(_cust_id)){
					retList.add(shopMap);
				}
			}
		}
		return retList;
	}
	
	public List getCollectListByMap(Map map){
		List list = collectDao.getCollectListByMap(map);
		return list;
	}
	
	public void delete(String id){
		this.collectDao.delete(id);
	}
	
}
