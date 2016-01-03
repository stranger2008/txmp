package com.xingfugo.web.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
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
 * @author 创建人 陈显革
 * @date 创建日期 Fri Sep 12 09:58:45 CST 2014
 */
 
@Controller
public class GoodsAuditController extends BaseController{

	//商品类别根节点
	private static final String CATEGOEY_ROOT = "1111111111";
	//商品类别类型
	private static final String CATEGOEY_MODULE_TYPE = "goods";
	//字符串结尾的逗号(正则表达式
	private static final String THE_COMMA_END_OF_STRING = "(?:,)*$";
	//0：未审核 1：审核通过 2：审核不通过 3：禁用
	private static final String GOODS_STATUS_UNAUDIT = "0";
	private static final String GOODS_STATUS_REJECT = "2";
	//分类显示
	private static final String SHOW = "0";
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private InfoAttrService infoAttrService;
	
	//列表
	@RequestMapping(value="goods-audit/index")
	public String list(GoodsQueryForm goodsQueryForm,ModelMap model) throws Exception {
		//替换掉最后的逗号
		String cat_attr = goodsQueryForm.getCat_attr() == null ? null :goodsQueryForm.getCat_attr().replaceAll(THE_COMMA_END_OF_STRING, "");
		goodsQueryForm.setCat_attr(cat_attr);
		//正常和禁用
		String info_states = GOODS_STATUS_UNAUDIT + "," + GOODS_STATUS_REJECT;
		goodsQueryForm.setInfo_states(info_states);
		goodsQueryForm.setIs_del("1");
		PageResult pageResult = goodsService.getListByPage(goodsQueryForm);
		pageResult.setList(this.cookGoodsCategory((List<Map<String, Object>>)pageResult.getList()));
		pageOper(model, pageResult);
		model.addAttribute("goodsQueryForm", goodsQueryForm);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("up_cat_id", CATEGOEY_ROOT);
		map.put("module_type", CATEGOEY_MODULE_TYPE);
		map.put("is_display", SHOW);
		List categorys = categoryService.getList(map);
		model.put("categorys", categorys);
		
		return "goodsaudit/index";
	}
	
	//查看
	@RequestMapping(value="goods-audit/view",method=RequestMethod.GET)
	public String view(String id, ModelMap model, RedirectAttributes rAttr) throws Exception {
		Goods goods = goodsService.getByPk(id);
		if(goods == null) {
			operatePrompt(rAttr, "商品不存在");
			return "redirect:"+basePath()+"goods-audit/index.action";
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
		return "goodsaudit/view";
	}
	
	//进入修改
	@RequestMapping(value="goods-audit/update",method=RequestMethod.GET)
	public String update(String id, ModelMap model, RedirectAttributes rAttr) throws Exception {
		Goods goods = goodsService.getByPk(id);
		if(goods == null) {
			operatePrompt(rAttr, "商品不存在");
			return "redirect:"+basePath()+"goods-audit/index.action";
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
		return "goodsaudit/update";
	}
	
	//修改
	@RequestMapping(value="goods-audit/update",method=RequestMethod.POST)
	public String update(@Valid Goods goods, Errors errors, RedirectAttributes rAttr, ModelMap model) throws Exception {
		String info_state = goods.getInfo_state();
		if(info_state == null || info_state.length() != 1) {
			errors.rejectValue("info_state", null, "审核状态不能为空且长度必须为1");
		}
		
		String no_reason = goods.getNo_reason();
		if(GOODS_STATUS_REJECT.equals(info_state)) {
			if(no_reason == null || no_reason.trim().length() == 0 || no_reason.length() > 100) {
				errors.rejectValue("no_reason", null, "原因不能为空,且长度不能大于100");
			}
		}
		if (errors.hasErrors()){
			//图片
			goods = goodsService.getByPk(String.valueOf(goods.getGoods_id()));
			goods.setInfo_state(info_state);
			goods.setNo_reason(no_reason);
			
			List<String> imgs = new ArrayList<String>();
			String img_path = goods.getImg_path();
			if(img_path != null && img_path.trim().length() > 0) {
				String[] imgArr = img_path.trim().split(",");
				for(int i=0; i<imgArr.length; i++) {
					imgs.add(ImgPathUitls.filterImagePath_spec(imgArr[i],70,70));
				}
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
			
			model.put("goods", goods);
			model.put("infoattrs", infoattrs);
			model.put("imgs", imgs);
	        return "goodsaudit/update";
		}
		
		Goods oGood = goodsService.getByPk(String.valueOf(goods.getGoods_id()));
		if(oGood == null) {
			operatePrompt(rAttr, "商品不存在");
			return "redirect:"+basePath()+"goods-audit/index.action";
		}
		//非未审核状态
		if(!GOODS_STATUS_UNAUDIT.equals(oGood.getInfo_state())) {
			operatePrompt(rAttr, "商品状态不正确,无法审核");
			return "redirect:"+basePath()+"goods-audit/index.action";
		}
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("goods_id", String.valueOf(goods.getGoods_id()));
		map.put("info_state", goods.getInfo_state());
		map.put("no_reason", goods.getNo_reason());
		goodsService.updateGoodsStatus(map);
		operatePrompt(rAttr, "审核商品完成");
		return "redirect:"+basePath()+"goods-audit/index.action";
	}
	
	//删除
	@RequestMapping(value="goods-audit/delete",method=RequestMethod.POST)
	public String delete(String id,RedirectAttributes rAttr) throws Exception {
		goodsService.delete(id);
		operatePrompt(rAttr, "删除商品成功");
		return "redirect:"+basePath()+"goods-audit/index.action";
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
	
}

