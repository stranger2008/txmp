package com.xingfugo.web.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingfugo.business.common.Constants;
import com.xingfugo.business.module.AttrValue;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.business.service.CartService;
import com.xingfugo.business.service.CategoryService;
import com.xingfugo.business.service.CollectService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.GoodsbrandService;
import com.xingfugo.business.service.GoodsevalService;
import com.xingfugo.business.service.MemberService;
import com.xingfugo.business.service.NavService;
import com.xingfugo.business.service.SellerevalService;
import com.xingfugo.business.service.ShopconfigService;
import com.xingfugo.business.service.UserCartService;
import com.xingfugo.business.service.VisitorCartService;
import com.xingfugo.util.ImgPathUitls;
import com.xingfugo.web.client.common.SessionUtil;

//商品管理
@Controller
public class GoodsController extends BaseController{
	
	private final static String SESSION_AERA_ID = "session_area_id";
	
	private final static String  SOURCE= "web";
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private GoodsbrandService goodsbrandService;
	@Autowired
	private GoodsevalService goodsevalService;
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private SellerevalService sellerevalService;
	@Autowired
	private ShopconfigService shopconfigService;
	
	@Autowired
	private VisitorCartService visitorCartService;
	@Autowired
	private UserCartService userCartService;
	
	@Autowired
	private CollectService collectService;
	
	private CartService cartService;
	
	@Autowired
	private NavService navService;
	
	
	
	//商品未删除
	private static final String GOODS_NO_DEL = "1";
	//商品状态：正常
	private static final String GOODS_INFO_STATE_OK = "1";
	//商品品牌：正常
	private static final String GOODS_BRAND_STATE_OK = "0";
	//赞
	public static final String GOODNUM_COOKIE_NAME = "good_num";
	public static final String SESSION_GOODNUM_ID = "session_goodnum_id";

	public static final String SESSION_GOOD_NUM = "session_good_num";
	
	
	//商品列表
	@SuppressWarnings("unchecked")
	@RequestMapping(value="goodslist")
	public String goodslist(GoodsQueryForm goodsQueryForm,ModelMap model,HttpServletResponse response){
		String[] tradeIds = getRequest().getParameterValues("tradeIds");
		List<String> tradeIdList = null;
		if(tradeIds!=null && tradeIds.length>0 ){
			tradeIdList = new ArrayList<String>();
			List<String> params = new ArrayList<String>();
			for(String id: tradeIds){
				if(StringUtils.isEmpty(id.trim())){
					continue;
				}
								
				tradeIdList.add(id);
				if(id.indexOf(",")!=-1){
					id = id.replace(",", "%");
				} 
				params.add(id);
			}
			if(params.size()>0){
				goodsQueryForm.setValIdList(params);
			}
		}
		//选子站的时候放入了session
		//如果不为空说明取了子站，默认商品列表页按照地区过滤
		String session_area_id = SessionUtil.getString(getRequest(),SESSION_AERA_ID);
		if(!session_area_id.equals("")){
			goodsQueryForm.setArea_id(session_area_id);
		}
		
		//找出分类位置
		String cat_id = goodsQueryForm.getCat_id();
		if(!StringUtils.isBlank(cat_id)){
			List posTreeList = new ArrayList();
			categoryService.setCatTreeByCatid(posTreeList,cat_id);
			model.addAttribute("posTreeList", posTreeList);
		}
		
		//商品列表
		goodsQueryForm.setIs_onsell(true);
		goodsQueryForm.setIs_del(GOODS_NO_DEL);
		goodsQueryForm.setInfo_state(GOODS_INFO_STATE_OK);
		pageOper(model,goodsService.getGoodsListByPage_web(goodsQueryForm));
//		model.addAttribute("g",goodsQueryForm);
		model.addAttribute("goodsQueryForm",goodsQueryForm);
		
		String now_brand_id = goodsQueryForm.getBrand_id();
		if(StringUtils.isEmpty(now_brand_id)){
			//商品品牌
			Map map =new HashMap();
			map.put("goods_attr", goodsQueryForm.getCat_id());
			map.put("info_state", GOODS_BRAND_STATE_OK);
			model.addAttribute("goodsBands",goodsbrandService.selectByGoodsAttr(map));
			
		}
		
		
		//商品属性
		model.addAttribute("goodsAttr",goodsService.getGoodsAttr(goodsQueryForm));
		//热销商品
		model.addAttribute("saleGoods",goodsService.getGoodsOfUserLikeWeGuess());
		//获取已经选择属性信息
		List<AttrValue> selInfoMap = goodsService.getSearchInfoName(tradeIdList);
		if(!StringUtils.isEmpty(now_brand_id)){
			if(selInfoMap==null){
				selInfoMap = new ArrayList<AttrValue>();
			}
			AttrValue attr = new AttrValue();
			attr.setTrade_id(now_brand_id);
			attr.setAttr_name(goodsQueryForm.getBrand_name());
			selInfoMap.add(attr);
		}
		model.addAttribute("selInfoMap",selInfoMap);
		//商品列表导航
		List navlist = navService.getallList();
		model.addAttribute("navlist",navlist);
		
		return "goodslist";
	}
	/**
	 * 商品搜索
	 * @return
	 */
	@RequestMapping(value="searchGoods")
	public String searchGoods(GoodsQueryForm goodsQueryForm,ModelMap model,HttpServletResponse response){
		//选子站的时候放入了session
		//如果不为空说明取了子站，默认商品列表页按照地区过滤
		String session_area_id = SessionUtil.getString(getRequest(),SESSION_AERA_ID);
		if(!session_area_id.equals("")){
			goodsQueryForm.setArea_id(session_area_id);
		}
		//商品列表
		goodsQueryForm.setIs_onsell(true);
		goodsQueryForm.setIs_del(GOODS_NO_DEL);
		goodsQueryForm.setInfo_state(GOODS_INFO_STATE_OK);
		pageOper(model,goodsService.getGoodsListByPage_web(goodsQueryForm));
//		model.addAttribute("g",goodsQueryForm);
		model.addAttribute("goodsQueryForm",goodsQueryForm);
		
		//热销商品
		model.addAttribute("saleGoods",goodsService.getGoodsOfUserLikeWeGuess());
		return "goodsSearch";
	}
	//商品详细
	@SuppressWarnings("unchecked")
	@RequestMapping(value="goods/{id}",method=RequestMethod.GET)
	public String goodsdetail(@PathVariable(value = "id") Integer id,ModelMap model,HttpServletRequest request,HttpServletResponse response){
		
		Map<String, Object> goodsMap = goodsService.getGoodsDetailById(id,SOURCE);
		Map<String, String> imgSizes = new HashMap<String, String>();
		imgSizes.put("imagePath_70", "70_70");
		imgSizes.put("imagePath_350", "350_350");
		ImgPathUitls.filterImagePath_spec(goodsMap , "img_path", imgSizes);
		model.addAttribute("goods", goodsMap);
		if(!goodsMap.isEmpty()){
			List attrsList =(List)goodsMap.get("attrs");
			model.addAttribute("goodsAttrs", attrsList);
		}
		
		//某商品的评价总人数
		double memsum = 0;
		List<Map<String, Object>> evallsummember = goodsevalService.getsummember(String.valueOf(id));
		if(evallsummember.size()>0){
			memsum = Integer.parseInt(evallsummember.get(0).get("summember").toString());
		}
		
		//获取好评、中评、差评个数
		List<Map<String, Object>> evalgoodnum = goodsevalService.getgoodsnum(String.valueOf(id));
		int best = 0;
		int better = 0;
		int bad = 0;
		double bestnum = 0;
		double betternum = 0;
		double badnum = 0;
		for(Map o : evalgoodnum){
			//评数		
			if(o.get("g_eval").equals("2")){
				 best = (int) (Integer.parseInt(o.get("sum").toString())/memsum*100);
				 bestnum = Integer.parseInt(o.get("sum").toString());
				 
			}else if(o.get("g_eval").equals("1")){
				 better = (int) (Integer.parseInt(o.get("sum").toString())/memsum*100);
				 betternum = Integer.parseInt(o.get("sum").toString());
				 
			}else if(o.get("g_eval").equals("0")){
				 bad = (int) (Integer.parseInt(o.get("sum").toString())/memsum*100);
				 badnum = Integer.parseInt(o.get("sum").toString());
				 
			}
		}
		model.addAttribute("badnum",(int)badnum );
		model.addAttribute("betternum",(int)betternum );
		model.addAttribute("bestnum",(int)bestnum );
		model.addAttribute("memsum", (int)memsum);
		model.addAttribute("best", best);
		model.addAttribute("better", better);
		model.addAttribute("bad", bad);
		
		//显示商家信息
		if(goodsMap != null){			
			List<Map<String, Object>> memberinfo = goodsevalService.getSellerinfo(goodsMap.get("cust_id").toString());
			model.addAttribute("memberinfo", memberinfo);
		}
		
		// 商品评价显示
		List<Map<String, Object>> evallist = goodsevalService.getGoodsevallist(String.valueOf(id));
		model.addAttribute("evallist", evallist);
		
		//服务 速度 包装星评
		List<Map<String, Object>> stareval = sellerevalService.getStareval(goodsMap.get("cust_id").toString());
		model.addAttribute("stareval", stareval);
		
		//面包屑网页位置，根据分类ID串获取分类名称串List
		if(goodsMap!=null && goodsMap.get("cat_attr")!=null && !goodsMap.get("cat_attr").toString().equals("")){
			String cat_attr = goodsMap.get("cat_attr").toString();
			List posTreeList = categoryService.getDetailCatTree(cat_attr);
			model.addAttribute("posTreeList", posTreeList);
		}else{
			return "404";
		}
		model.addAttribute("goods_id", id);
		
		return "goodsdetail";
	}
	
	
	private List<Map<String, Object>> getHasGoodInCookies(Cookie[] cookies, List<Map<String, Object>> evallist,HttpServletRequest request) {
		
        if (cookies != null) {
            for(Cookie cookie : cookies) {
            	 if (cookie.getName().contains("good_num")) {
            		 String[] values = cookie.getValue().split("\\|");
            			Iterator itr = evallist.iterator();
            			while (itr.hasNext()) {
            				Map aboutusMap = (Map) itr.next();
            				String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
            				String goods_id="";
            				if (aboutusMap.get("goods_id") != null && aboutusMap.get("ge_tradeid") != null) {
            					if(values.length>1){
            						if(user_id.equals(values[1])  && aboutusMap.get("ge_tradeid").toString().equals(values[2]) ){
            							aboutusMap.put("isZan", "true");
            						}
            					}
            					
            				}
            			}
            	 }
            }
        }
		return evallist;
	}

	
	@RequestMapping(value="setGoodNumCookie",method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String setGoodNumCookie( String buy_cust_id, String goods_id,String trade_id,String goodnum,HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception {

		String user_id = SessionUtil.getString(request,Constants.SESSION_USER_ID);
		
		String good_num = String.valueOf(Integer.parseInt(goodnum)+1);
		Map param = new HashMap();
		param.put("good_num", good_num);
		param.put("buy_cust_id", user_id);
		param.put("goods_id", goods_id);
		param.put("trade_id", trade_id);
		goodsevalService.updateBytradeid(param);
		
		Cookie[] cookies = this.getRequest().getCookies();

		Cookie cookie = null;
		// 获取
		cookie=new Cookie("good_num"+trade_id+"&&"+goods_id,goods_id+"|"+user_id+"|"+trade_id);
		cookie.setPath("/");
		response.addCookie(cookie);
		return "1";
		
	}
	
	
	
	@RequestMapping(value="showSelecteval",method=RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String showSelecteval(String g_eval,String goods_id,HttpServletRequest request) throws Exception {
		// 商品评价显示
		Cookie[] cookies =  request.getCookies();
		if(!g_eval.equals("3")){
			Map param = new HashMap();
			param.put("g_eval", g_eval);
			param.put("goods_id", goods_id);
			List<Map<String, Object>> evallist = goodsevalService.showSelecteval(param);
			evallist = getHasGoodInCookies(cookies,evallist,request);
			JSONArray jsonArray = JSONArray.fromObject(evallist);
			return jsonArray.toString();
		}else{
			List<Map<String, Object>> evallist = goodsevalService.getGoodsevallist(goods_id);
			evallist = getHasGoodInCookies(cookies,evallist,request);
			JSONArray jsonArray = JSONArray.fromObject(evallist);
			return jsonArray.toString();
		}
	}
	
}
