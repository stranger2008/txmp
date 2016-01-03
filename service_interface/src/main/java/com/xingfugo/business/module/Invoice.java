package com.xingfugo.business.module;

import org.hibernate.validator.constraints.NotEmpty;

//发票对象
//隶属于订单实体
public class Invoice{
	@NotEmpty
	private String invoice_type;
	private String invoice_top;
	private String company_name;
	private String ident_number;
	private String regis_address;
	private String regis_tel;
	private String open_bank;
	private String bank_account;
	private String invoice_content;
	
	public Invoice(){
	}
	
	public Invoice(String invoice_type, String invoice_top,
			String company_name, String ident_number, String regis_address,
			String regis_tel, String open_bank, String bank_account,
			String invoice_content) {
		this.invoice_type = invoice_type;
		this.invoice_top = invoice_top;
		this.company_name = company_name;
		this.ident_number = ident_number;
		this.regis_address = regis_address;
		this.regis_tel = regis_tel;
		this.open_bank = open_bank;
		this.bank_account = bank_account;
		this.invoice_content = invoice_content;
	}
	public String getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(String invoice_type) {
		this.invoice_type = invoice_type;
	}
	public String getInvoice_top() {
		return invoice_top;
	}
	public void setInvoice_top(String invoice_top) {
		this.invoice_top = invoice_top;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getIdent_number() {
		return ident_number;
	}
	public void setIdent_number(String ident_number) {
		this.ident_number = ident_number;
	}
	public String getRegis_address() {
		return regis_address;
	}
	public void setRegis_address(String regis_address) {
		this.regis_address = regis_address;
	}
	public String getRegis_tel() {
		return regis_tel;
	}
	public void setRegis_tel(String regis_tel) {
		this.regis_tel = regis_tel;
	}
	public String getOpen_bank() {
		return open_bank;
	}
	public void setOpen_bank(String open_bank) {
		this.open_bank = open_bank;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public String getInvoice_content() {
		return invoice_content;
	}
	public void setInvoice_content(String invoice_content) {
		this.invoice_content = invoice_content;
	}
	
}
