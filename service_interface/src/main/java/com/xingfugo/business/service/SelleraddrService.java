package com.xingfugo.business.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.SelleraddrDao;
import com.xingfugo.business.module.Selleraddr;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;

/**
 * @function 功能 卖家发货地址Service层业务实现
 * @author 创建人 史倩倩
 * @date 创建日期 Wed Sep 24 19:11:11 CST 2014
 */

@Service
public class SelleraddrService extends GenericService<Selleraddr,String>{
	@Autowired
	private SelleraddrDao selleraddrDao;
	@Autowired
	private AreaService areaService;
	
	public SelleraddrService() {}
	
	@Autowired
	public SelleraddrService(SelleraddrDao selleraddrDao) {
		super(selleraddrDao);
		this.selleraddrDao = selleraddrDao;
	}
	public void updateSelleraddr(Selleraddr selleraddr) {
		updateDefaultAddrInfo(selleraddr);
		selleraddrDao.update(selleraddr);
		
	}
	private void updateDefaultAddrInfo(Selleraddr selleraddr) {
		if("1".equals(selleraddr.getIs_default())){
			selleraddrDao.updateIsDefaultAddr(selleraddr);
		}
		
	}

	/**
	 * 获取商家默认地址
	 * @author liuxl
	 * @param cust_id 商家id
	 * @return
	 */
	public Selleraddr getDefaultBuyeraddr(String cust_id){
		return selleraddrDao.getDefaultSelleraddr(cust_id);
	}
	
	@Override
	public PageResult getListByPage(BasePageForm basePageForm) {
		List<Map<String, Object>> list = selleraddrDao.getListByPage(basePageForm);
		Iterator itr = list.iterator();
		while(itr.hasNext()){
			Map amap = (Map)itr.next();
			String area_attr = "";
			String address = "";
			if(amap.get("address")!=null){
				address = amap.get("address").toString();
			}
			if(amap.get("area_attr")!=null){
				area_attr = amap.get("area_attr").toString();
			}
			
			String area_name_str = areaService.getAreaAttrNameByAreaIdAttr(area_attr) + address;
			amap.put("area_name_str", area_name_str);
		}
		PageResult result = PageResultBuilder.build(basePageForm.getPg(), list);
		return result;
	}
	
}

