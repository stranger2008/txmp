package com.xingfugo.web.admin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xingfugo.business.module.Goods;
import com.xingfugo.business.module.mybatis.PageResult;
import com.xingfugo.business.module.query.GoodsQueryForm;
import com.xingfugo.business.service.CategoryService;
import com.xingfugo.business.service.GoodsService;
import com.xingfugo.business.service.InfoAttrService;
import com.xingfugo.util.ImgPathUitls;

/**
 * @function 功能 商品Service层业务实现
 *
 */
 
@Controller
public class GoodsdelController extends BaseController{

	private static final String CATEGOEY_ROOT = "1111111111";
	private static final String CATEGOEY_MODULE_TYPE = "goods";
	//字符串结尾的逗号(正则表达式
	private static final String THE_COMMA_END_OF_STRING = "(?:,)*$";
	//0：未审核 1：审核通过 2：审核不通过 3：禁用
	private static final String GOODS_STATUS_ENABLE = "1";
	private static final String GOODS_STATUS_DISABLE = "3";
	//分类显示
	private static final String SHOW = "0";
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private InfoAttrService infoAttrService;
	
	//列表
	@RequestMapping(value="goodsdel/index")
	public String list(GoodsQueryForm goodsQueryForm,ModelMap model) throws Exception {
		//替换掉最后的逗号
		String cat_attr = goodsQueryForm.getCat_attr() == null ? null :goodsQueryForm.getCat_attr().replaceAll(THE_COMMA_END_OF_STRING, "");
		goodsQueryForm.setCat_attr(cat_attr);
	
		goodsQueryForm.setIs_del("0");
		PageResult pageResult = goodsService.getListByPage(goodsQueryForm);
		pageResult.setList(this.cookGoodsCategory((List<Map<String, Object>>)pageResult.getList()));
		pageOper(model, pageResult);
		model.addAttribute("goodsQueryForm", goodsQueryForm);
		
		//查询商品分类
		Map<String, String> map = new HashMap<String, String>();
		map.put("up_cat_id", CATEGOEY_ROOT);
		map.put("module_type", CATEGOEY_MODULE_TYPE);
		map.put("is_display", SHOW);
		List categorys = categoryService.getList(map);
		model.put("categorys", categorys);
		
		return "goodsdel/index";
	}
	
	//单个商品还原
	@RequestMapping(value="goodsdel/return", method=RequestMethod.GET)
	public String deleteGoods(Integer goodsId, RedirectAttributes rAttr){
		goodsService.returnByPrimaryKey(goodsId);
			operatePrompt(rAttr, "成功还原商品");
		return "redirect:/goodsdel/index.action";
	}
	//商品批量还原
	@RequestMapping(value="goodsdel/batchreturn", method=RequestMethod.POST)
	public String batchDeleteGoods(Integer[] goodsId, RedirectAttributes rAttr){
		if(goodsId == null || goodsId.length == 0){
			return "redirect:/goodsdel/list.action";
		}
		List<Integer> delGoodsIdList = Arrays.asList(goodsId);
		goodsService.batchreturn(delGoodsIdList);
		operatePrompt(rAttr, "成功还原["+delGoodsIdList.size()+"]个商品");
		return "redirect:/goodsdel/index.action";
	}
	//查看
	@RequestMapping(value="goodsdel/view",method=RequestMethod.GET)
	public String view(String id, ModelMap model, RedirectAttributes rAttr) throws Exception {
		Goods goods = goodsService.getByPk(id);
		if(goods == null) {
			operatePrompt(rAttr, "商品不存在");
			return "redirect:"+basePath()+"goodsdel/index.action";
		}
		String cat_attr = goods.getCat_attr();
		if(cat_attr != null && !cat_attr.trim().equals("")) {
			//查询分类名称
			Map<String, String> categorys = categoryService.getCatMapByIds(cat_attr.trim());
			if(categorys != null) {
				String cat_names = "";
				String[] cateArr = cat_attr.trim().split(",");
				for(String cate : cateArr) {
					cat_names = cat_names + "," + categorys.get(cate);
				}
				if(!"".equals(cat_names)) {
					goods.setCat_names(cat_names.substring(1));
				}
			}
		}
		
		//图片
		List<String> imgs = new ArrayList<String>();
		List<String> imgsmaster = new ArrayList<String>();
		String img_path = goods.getImg_path();
		if(img_path != null && img_path.trim().length() > 0) {
			String[] imgArr = img_path.trim().split(",");
			for(int i=0; i<imgArr.length; i++) {
				imgs.add(ImgPathUitls.filterImagePath_spec(imgArr[i],70,70));
				imgsmaster.add(ImgPathUitls.filterImagePath(imgArr[i]));
			}
		}
		
		//分类属性
		String infoattr_id = goods.getInfoattr_id();
		List<Map<String, Object>> infoattrList = infoAttrService.selectInfoAttrsById(infoattr_id);
		Map<String, List<Map<String, Object>>> infoattrs = new HashMap<String, List<Map<String, Object>>>();
		if(infoattrList != null) {
			for(Map<String, Object> infoattr : infoattrList) {
				String attr_id = (String) infoattr.get("attr_id");
				List<Map<String, Object>> ias = infoattrs.get(attr_id);
				if(ias == null) {
					ias = new ArrayList<Map<String, Object>>();
					infoattrs.put(attr_id, ias);
				}
				ias.add(infoattr);
				
			}
		}
		
		model.put("infoattrs", infoattrs);
		model.put("imgs", imgs);
		model.put("imgsmaster", imgsmaster);
		model.addAttribute("goods", goods);
		return "goods/view";
	}
	
	/**
	 * 把给商品添加商品分类名称(cate_names)
	 * @param goodsList
	 * @return
	 */
	private List<Map<String, Object>> cookGoodsCategory(List<Map<String, Object>> goodsList) {
		Set<String> categoryIds = new HashSet<String>();
		if(goodsList != null && goodsList.size() > 0) {
			//遍历商品,把商品的所属分类放进列表中
			for(Map<String, Object> goods : goodsList) {
				String cate_attr = (String) goods.get("cat_attr");
				if(cate_attr != null && (!"".equals(cate_attr.trim()))) {
					String[] cateArr = cate_attr.trim().split(",");
					for(String cate : cateArr) {
						if(cate != null && (!"".equals(cate.trim()))) {
							categoryIds.add(cate.trim());
						}
					}
				}
			}
		} else {
			return goodsList;
		}
		
		//所有分类id串
		String cIds = "";
		
		for(String cid: categoryIds) {
			cIds = cIds + "," + cid;
		}
		
		if("".equals(cIds)) {
			return goodsList;
		}
		cIds = cIds.substring(1);
		
		//查询所有分类id串
		Map<String, String> categorys = categoryService.getCatMapByIds(cIds);
		if(categorys == null) {
			return goodsList;
		}
		
		for(Map<String, Object> goods : goodsList) {
			String cate_attr = (String) goods.get("cat_attr");
			if(cate_attr != null && (!"".equals(cate_attr.trim()))) {
				String cate_names = "";
				String[] cateArr = cate_attr.trim().split(",");
				for(String cate : cateArr) {
					cate_names = cate_names + "," + categorys.get(cate);
				}
				if(!"".equals(cate_names)) {
					goods.put("cate_names", cate_names.substring(1));
				}
			}
		}
		return goodsList;
	}
	//删除
	@RequestMapping(value="goodsdel/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		goodsService.delete(id);
		operatePrompt(rAttr, "删除卖家发货地址成功");
		return "redirect:"+basePath()+"goodsdel/index.action";
	}
	
}
