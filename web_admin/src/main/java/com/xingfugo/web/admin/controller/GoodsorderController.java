package com.xingfugo.web.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingfugo.business.module.Inc_shipinfo;
import com.xingfugo.business.module.Order_goods_desc;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsorderQueryForm;
import com.xingfugo.business.service.CommparaService;
import com.xingfugo.business.service.GoodsorderService;
import com.xingfugo.business.service.Inc_shipinfoService;
import com.xingfugo.business.service.OrderdetailService;
import com.xingfugo.util.ImgPathUitls;

/**
 * @function 功能 订单信息Service层业务实现
 * @author 创建人 陈显革
 * @date 创建日期 Tue Sep 23 10:50:06 CST 2014
 */
 
@Controller
public class GoodsorderController extends BaseController{
	
	//数据字典key
	private static final String ORDER_STATE = "order_state";
	//等待买家确认
	private static final String GOODSORDER_STATE_CONFIRM = "2";
	//交易成功
	private static final String GOODSORDER_STATE_SUCCESS = "4";
	//申请退货
	private static final String GOODSORDER_STATE_RETURN = "5";
	//评价成功
	private static final String GOODSORDER_STATE_EVALUATE = "6";
	
	//有物流信息的订单类型
	private static List<String> GOODSORDER_STATE_HAS_SHIP_INFO = new ArrayList<String>();
	
	static {
		GOODSORDER_STATE_HAS_SHIP_INFO.add(GOODSORDER_STATE_CONFIRM);
		GOODSORDER_STATE_HAS_SHIP_INFO.add(GOODSORDER_STATE_SUCCESS);
		GOODSORDER_STATE_HAS_SHIP_INFO.add(GOODSORDER_STATE_RETURN);
		GOODSORDER_STATE_HAS_SHIP_INFO.add(GOODSORDER_STATE_EVALUATE);
	}
	
	@Autowired
	private GoodsorderService goodsorderService;
	
	@Autowired
	private OrderdetailService orderdetailService;
	
	@Autowired
	private CommparaService commparaService;
	//物流运送信息Service层业务
	@Autowired
	private Inc_shipinfoService inc_shipinfoService;
	
	//列表
	@RequestMapping(value="goodsorder/index")
	public String list(GoodsorderQueryForm goodsorderQueryForm,ModelMap model) throws Exception {
		
		Map orderStates = this.commparaService.getSelectMapByParacode(ORDER_STATE);
		model.put("orderStates", orderStates);
		
		PageResult pageResult = goodsorderService.getListByPage(goodsorderQueryForm);
		List result = pageResult.getList();
		if(result != null) {
			for(Map<String, Object> bean : (List<Map<String, Object>>) result) {
				//把订单商品串转换成Order_goods_desc对象,并处理图片路径
				String order_goods_desc = (String) bean.get("order_goods_desc");
				
				if(order_goods_desc != null) {
					//订单商品
					List<Order_goods_desc> ordergoods = new ArrayList<Order_goods_desc>();
					
					JSONArray jsonArray = JSONArray.fromObject(order_goods_desc);
					for(int i=0; i<jsonArray.size(); i++) {
						JSONObject jsonObject = (JSONObject) jsonArray.get(i);
						Order_goods_desc ogd = (Order_goods_desc) JSONObject.toBean(jsonObject, Order_goods_desc.class);
						ogd.setImg(ImgPathUitls.filterImagePath_spec(ogd.getImg(),70,70));
						ordergoods.add(ogd);
					}
					bean.put("ordergoods", ordergoods);
				}
				
//				if(order_goods_desc != null) {
//					List<Map<String, Object>> beans = this.parseJSON2List(order_goods_desc);
//					
//					bean.put("ordergoods", beans);
//				}
			}
		}
		
		pageOper(model, pageResult);
		model.addAttribute("goodsorderQueryForm", goodsorderQueryForm);
		return "goodsorder/index";
	}
	
	//查看详细
	@RequestMapping(value="goodsorder/view",method=RequestMethod.GET)
	public String view(String order_id,ModelMap model) throws Exception {
		Map<String, Object> goodsorder = goodsorderService.selectGoodsorderDetailById(order_id);
		model.addAttribute("goodsorder", goodsorder);
		
		//物流信息
		Inc_shipinfo inc_shipinfo = new Inc_shipinfo();
		String order_state = String.valueOf(goodsorder.get("order_state"));
		if(GOODSORDER_STATE_HAS_SHIP_INFO.contains(order_state)) {
			Map<String, String> rmap = new HashMap<String, String>();
			rmap.put("hasShipName", "1");
			rmap.put("link_id", String.valueOf(order_id));
			List<Map<String, Object>> inc_shipinfos = inc_shipinfoService.getList(rmap);
			if(inc_shipinfos == null || inc_shipinfos.size() != 0) {
				inc_shipinfo.setTrade_id(inc_shipinfos.get(0).get("trade_id") == null ? null : String.valueOf(inc_shipinfos.get(0).get("trade_id")));
				inc_shipinfo.setShip_id(inc_shipinfos.get(0).get("ship_id") == null ? null : (String)inc_shipinfos.get(0).get("ship_id"));
				inc_shipinfo.setLink_id(inc_shipinfos.get(0).get("link_id") == null ? null : (String)inc_shipinfos.get(0).get("link_id"));
				inc_shipinfo.setShip_no(inc_shipinfos.get(0).get("ship_no") == null ? null : (String)inc_shipinfos.get(0).get("ship_no"));
				inc_shipinfo.setShip_img(inc_shipinfos.get(0).get("ship_img") == null ? null : ImgPathUitls.filterImagePath((String)inc_shipinfos.get(0).get("ship_img")));
				inc_shipinfo.setShip_desc(inc_shipinfos.get(0).get("ship_desc") == null ? null : (String)inc_shipinfos.get(0).get("ship_desc"));
				inc_shipinfo.setUser_id(inc_shipinfos.get(0).get("user_id") == null ? null : String.valueOf(inc_shipinfos.get(0).get("user_id")));
				inc_shipinfo.setUser_type(inc_shipinfos.get(0).get("user_type") == null ? null : (String)inc_shipinfos.get(0).get("user_type"));
				inc_shipinfo.setIn_date(inc_shipinfos.get(0).get("in_date") == null ? null : (Date)inc_shipinfos.get(0).get("in_date"));
				inc_shipinfo.setShip_name(inc_shipinfos.get(0).get("ship_name") == null ? null : (String)inc_shipinfos.get(0).get("ship_name"));
			}
		}
		model.addAttribute("inc_shipinfo", inc_shipinfo);
		
		List orderdetail = orderdetailService.getOrderdetailList(order_id);
		//处理图片路径
		ImgPathUitls.filterImagePath_spec(orderdetail,70,70);
		model.addAttribute("orderdetail", orderdetail);
		return "goodsorder/view";
	}
	
	/**
	 * json串转list<map>
	 * @param jsonStr
	 * @return
	 * @author 陈显革
	 */
	private static List<Map<String, Object>> parseJSON2List(String jsonStr){
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
        	JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
	}
	
	/**
	 * json串转map
	 * @param jsonStr
	 * @return
	 * @author 陈显革
	 */
	private static Map<String, Object> parseJSON2Map(String jsonStr){
		Map<String, Object> map = new HashMap<String, Object>();
		//最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
        	Object v = json.get(k);
            //如果内层还是数组的话，继续解析
            if(v instanceof JSONArray){
            	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
                Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                	JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map.put(k.toString(), list);
            } else {
            	map.put(k.toString(), v);
            }
        }
        return map;
	}
	
}

