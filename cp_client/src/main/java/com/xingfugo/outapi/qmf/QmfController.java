package com.xingfugo.outapi.qmf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.outapi.qmf.DefaultSecurityService;
import com.xingfugo.business.service.UserService;
import com.xingfugo.common.SessionUtil;
import com.xingfugo.controller.BaseController;

/**
 * @author 李良林
 *  全民付接口
 */
@Controller
public class QmfController extends BaseController{
	
	private static final String channelId = "100005";
	private static final String customizeId = "1105";
	private static final String qmfhost = "https://mpos.quanminfu.com/QmfWeb/";
	//https://mpos.quanminfu.com/QmfWeb/ 生产环境
	//http://116.228.21.170/qmfweb/ 测试环境
	
	
	@Autowired
	private UserService userService;
	
	/**
	 * 全民付接口
	 * @throws Exception 
	 */
	@RequestMapping(value="order/qmfweb-{bizName}",method=RequestMethod.GET)
	public String water(@PathVariable(value = "bizName") String bizName) throws Exception{
		//获取手机号
		int user_id = Integer.parseInt( SessionUtil.getString(getRequest(), Constants.SESSION_USER_ID) );
		String mobileNum = userService.getMemberUserById(user_id).getCellphone();
		//获取加密串
		String data = channelId + customizeId + mobileNum;
		//加密获取签名
		String signature = DefaultSecurityService.getSign(data);
		//拼接url地址
		String url = qmfhost+"?channelId="+channelId+"&customizeId="+customizeId+"&mobileNum="+mobileNum+"&bizName="+bizName+"&signature="+signature;
		return "redirect:"+url;
	}
	
}

