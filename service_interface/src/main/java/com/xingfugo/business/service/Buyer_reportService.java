package com.xingfugo.business.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.Buyer_reportDao;
import com.xingfugo.business.module.Buyer_report;
import com.xingfugo.business.module.mybatis.BasePageForm;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.util.PageResultBuilder;

/**
 * @function 功能 举报记录信息Service层业务实现
 * @author 创建人 陈晓艺
 * @date 创建日期 Mon Oct 20 11:43:07 CST 2014
 */

@Service
public class Buyer_reportService extends GenericService<Buyer_report,String>{

	private Buyer_reportDao buyer_reportDao;
	
	@Autowired
	private CommparaService commparaService;
	
	public Buyer_reportService() {}
	
	@Autowired
	public Buyer_reportService(Buyer_reportDao buyer_reportDao) {
		super(buyer_reportDao);
		this.buyer_reportDao = buyer_reportDao;
	}
	
	@Override
	public PageResult getListByPage(BasePageForm basePageForm){
		List<Map<String, Object>> list = buyer_reportDao.getListByPage(basePageForm);
		PageResult result = PageResultBuilder.build(basePageForm.getPg(), list);
		setReport_type(list);
		return result;
	}
	
	private void setReport_type(List list){
		List<Map> result = (List<Map>)list;
		if(result==null||result.size()<=0){
			return;
		}
		for(Map cl : result){
			String report_type = (String)cl.get("r_type");
			if(StringUtils.isBlank(report_type)){
				continue;
			}
			String report_type_s = this.commparaService.getParakeyByParacode("report_type",report_type);
			if(StringUtils.isNotBlank(report_type_s)){
				cl.put("report_type_s", report_type_s);
			}
		}
	}

}

