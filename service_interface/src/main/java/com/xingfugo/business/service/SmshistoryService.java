package com.xingfugo.business.service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.Sms_email_templateDao;
import com.xingfugo.business.dao.SmshistoryDao;
import com.xingfugo.business.module.Sms_email_template;
import com.xingfugo.business.module.Smshistory;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.SmshistoryQueryForm;
import com.xingfugo.util.PageResultBuilder;
import com.xingfugo.util.RandomStrUtil;
import com.xingfugo.util.SmsUtil;


@Service
public class SmshistoryService{

	@Autowired
	private SmshistoryDao smshistoryDao;
	@Autowired
	private MemberuserService memberuserService;
	
	@Autowired
	private Sms_email_templateDao sms_email_templateDao;
	
	private Sms_email_template sms_email_template;
	
	private static final String TEMP_CODE = "dynamic_check_code";
	
	public String getRegCodeByPhone(String phone){
		return smshistoryDao.getRegCodeByPhone(phone);
	}
	
	public String registerSendCheckCode(String phone){
		return sendSmsinfoByTempcode(phone);
	}
	//短信模板
	public String sendSmsinfoByTempcode(String phone){
		String check_code = RandomStrUtil.getSixNumberRand();
		String code = "";
		sms_email_template = sms_email_templateDao.getByPk(TEMP_CODE);
		if(sms_email_template != null)
		{
			String temp_con = sms_email_template.getTemp_con();
			if(temp_con != null && !"".equals(temp_con))
			{
				String sms_desc = temp_con.replace("{vary_code}",check_code);
				//非本人或授权操作，请致电4000-666-666
				try {
					code = SmsUtil.send(phone,sms_desc);
					//1：发送成功
					if(code.equals("1")){
						Smshistory smshistory = new Smshistory();
						smshistory.setPhoneattr(phone);
						smshistory.setContent(check_code);
						smshistory.setSms_desc(sms_desc);
						smshistoryDao.insertSmsInfo(smshistory);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return code;
	}
	//
	public boolean autoRegisteNotice(String phone, String passwd){
		String contentTemple = "尊敬的用户您好，您已自动注册为天下名品会员，登录帐号为您的手机号码【{0}】，初始密码为【{1}】，请您尽快登录到天下名品网站后，进行修改！";
		MessageFormat mf = new MessageFormat(contentTemple);
		String content = mf.format(new Object[]{phone, passwd});
		try {
			String code = SmsUtil.send(phone,content);
			//1：发送成功
			return code.equals("1");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getPhoneBySendDate(String phone){
		return smshistoryDao.getPhoneBySendDate(phone);
	}

	public List hasPhonenumber(String cellnumber) {
		return smshistoryDao.hasPhonenumber(cellnumber);
	}
	
	public PageResult getListByPage(SmshistoryQueryForm smshistoryQueryForm){
		List<Map<String, Object>> list = smshistoryDao.getListByPage(smshistoryQueryForm);
		PageResult result = PageResultBuilder.build(smshistoryQueryForm.getPg(), list);
		return result;
	}
	
	public void delete(String id){
		smshistoryDao.delete(id);
	}
}
