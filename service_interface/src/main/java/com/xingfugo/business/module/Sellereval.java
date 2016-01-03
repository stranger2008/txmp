package com.xingfugo.business.module;

import java.io.Serializable;
/**
 * @function 功能 商家评价实体
 * @author 创建人 史倩倩
 * @date 创建日期 Sat Oct 11 11:10:59 CST 2014
 */
public class Sellereval implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String order_id;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	private String cust_id;
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	
	private String eval_cust_id;
	public String getEval_cust_id() {
		return eval_cust_id;
	}
	public void setEval_cust_id(String eval_cust_id) {
		this.eval_cust_id = eval_cust_id;
	}
	
	private String package_score_eval;
	public String getPackage_score_eval() {
		return package_score_eval;
	}
	public void setPackage_score_eval(String package_score_eval) {
		this.package_score_eval = package_score_eval;
	}
	
	private String speed_score_eval;
	public String getSpeed_score_eval() {
		return speed_score_eval;
	}
	public void setSpeed_score_eval(String speed_score_eval) {
		this.speed_score_eval = speed_score_eval;
	}
	
	private String service_score_eval;
	public String getService_score_eval() {
		return service_score_eval;
	}
	public void setService_score_eval(String service_score_eval) {
		this.service_score_eval = service_score_eval;
	}
	
	private String eval_time;
	public String getEval_time() {
		return eval_time;
	}
	public void setEval_time(String eval_time) {
		this.eval_time = eval_time;
	}
	

}

