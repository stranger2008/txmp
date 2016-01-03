package com.xingfugo.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.Buyer_complaintDao;
import com.xingfugo.business.module.Buyer_complaint;
import com.xingfugo.business.module.Member;
import com.xingfugo.business.module.Memberuser;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;

/**
 * @function 功能 投诉记录信息Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Tue Oct 14 17:58:10 CST 2014
 */

@Service
public class Buyer_complaintService extends GenericService<Buyer_complaint,String>{

	private Buyer_complaintDao buyer_complaintDao;
	
	private Member member;
	
	@Autowired
	private CommparaService commparaService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberuserService memberuserService;
	
	public Buyer_complaintService() {}
	
	@Autowired
	public Buyer_complaintService(Buyer_complaintDao buyer_complaintDao) {
		super(buyer_complaintDao);
		this.buyer_complaintDao = buyer_complaintDao;
	}
	
	public List getComplaintByOrderId(String order_id)
	{
		return this.buyer_complaintDao.getComplaintByOrderId(order_id);
	}
	
	@Override
	public PageResult getListByPage(BasePageForm basePageForm){
		List<Map<String, Object>> list = buyer_complaintDao.getListByPage(basePageForm);
		PageResult result = PageResultBuilder.build(basePageForm.getPg(), list);
		setComplaintByCust_nameAndCom_type(list);
		return result;
	}
	
	//查找chance_id下的客户机会跟进记录
	private void setComplaintByCust_nameAndCom_type(List list){
		List<Map> result = (List<Map>)list;
		if(result==null||result.size()<=0){
			return;
		}
		for(Map cl : result){
			String com_type = (String)cl.get("com_type");
			if(StringUtils.isBlank(com_type)){
				continue;
			}
			String com_type_s = this.commparaService.getParakeyByParacode("comp_type",com_type);
			if(StringUtils.isNotBlank(com_type_s)){
				cl.put("com_type_s", com_type_s);
			}
		}
	}
	
}

