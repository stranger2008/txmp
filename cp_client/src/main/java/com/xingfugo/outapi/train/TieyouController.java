package com.xingfugo.outapi.train;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.controller.BaseController;

/**
 * @author 李良林
 *  铁友接口
 */
@Controller
public class TieyouController extends BaseController{
	
	/**
	 * 铁友页面跳转
	 * @throws Exception 
	 */
	@RequestMapping(value="trainTieyou",method=RequestMethod.GET)
	public String trainTieyou() throws Exception{
		return "outapi/train/tieyou";
	}
	
}

