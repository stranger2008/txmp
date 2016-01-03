package com.xingfugo.business.module.mybatis;

public class PageUtil {

	private int curPage = 0; // 当前页
	private int pageSize_search = 0; // 每页多少行
	private int endSize; // 用于not in(select top endSize id)不在多少行内
	private int totalRow; // 共多少行
	private int totalPage; // 共多少页

	public int getStart() {
		if (curPage > 1)
			return (curPage - 1) * pageSize_search;
		else
			return 0;
	}

	public int getEnd() {
		return pageSize_search;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {

		int temp = pageSize_search * (curPage - 1);
		this.setEndSize(temp);
		this.curPage = curPage;
	}

	public int getEndSize() {
		return endSize;
	}

	public void setEndSize(int endSize) {
		this.endSize = endSize;
	}

	public int getPageSize() {
		return pageSize_search;
	}

	public void setPageSize(int pageSize_search) {
		this.pageSize_search = pageSize_search;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		if(pageSize_search == 0){
			this.totalRow = 0;
		}else{
			totalPage = totalRow / pageSize_search;
			if (totalRow % pageSize_search > 0){
				totalPage = totalPage + 1;
			}
			this.totalRow = totalRow;
		}
	}

	public int getTotalPage() {

		return this.totalPage;
	}

	public String getToolsMenu() {
		StringBuffer str = new StringBuffer("");
		int next, prev;
		prev = curPage - 1;
		next = curPage + 1;

		if (curPage > 1) {
			str.append("<input type=\"button\" class=\"sy\" style=\"cursor:pointer\" onclick=\"this.form['pg.curPage'].value=1;this.form.submit();\" value=\"首页\" />&nbsp;");
		} else {
			str.append("<input type=\"button\" class=\"sy\" style=\"color:#cecece;\" value=\"首页\" />&nbsp;");
		}
		if (curPage > 1) {
			str.append("<input type=\"button\" class=\"up\" onclick=\"this.form['pg.curPage'].value=" + prev + ";this.form.submit();\" value=\"上页\" />&nbsp;");
		} else {
			str.append("<input type=\"button\" class=\"upno\" style=\"color:#cecece;\" value=\"上页\" />&nbsp;");
		}
		if (curPage < totalPage) {
			str.append("<input type=\"button\" class=\"next\" onclick=\"this.form['pg.curPage'].value=" + next + ";this.form.submit();\" value=\"下页\" />&nbsp;");
		} else {
			str.append("<input type=\"button\" class=\"nextno\" style=\"color:#cecece;\" value=\"下页\" />&nbsp;");
		}
		if (totalPage > 1 && curPage != totalPage) {
			str.append("<input type=\"button\" style=\"cursor:pointer\" class=\"sy\" onclick=\"this.form['pg.curPage'].value=" + totalPage + ";this.form.submit();\" value=\"末页\" />&nbsp;&nbsp;");
		} else {
			str.append("<input type=\"button\" class=\"sy\" style='color:#cecece;' value=\"末页\" />&nbsp;&nbsp;");
		}
		str.append(" 共" + totalRow + "条记录");
		str.append("  每页<SELECT size=1 name='pg.size' onchange='this.form.submit();'>");

		int pageLen[] = {3,10,15,20,50,100};
		for(int i=0;i<pageLen.length;i++){
			String psel = "";
			if (pageSize_search == pageLen[i]) {
				psel = " selected";
			}
			str.append("<OPTION value="+pageLen[i]+psel+">"+pageLen[i]+"</OPTION>");
		}
		
		str.append("</SELECT>");
		str.append("条 分" + totalPage + "页显示 转到");
		str.append("<SELECT size=1 name='pg.curPage' onchange='this.form.submit();'>");
		for (int i = 1; i < totalPage + 1; i++) {
			if (i == curPage) {
				str.append("<OPTION value=" + i + " selected>" + i + "</OPTION>");
			} else {
				str.append("<OPTION value=" + i + ">" + i + "</OPTION>");
			}
		}
		str.append("</SELECT>页");
		//str.append("<INPUT type=\"hidden\"  value=\"" + curPage + "\" name=\"pages_search\" /> ");
		//str.append("<INPUT type=\"hidden\"  value=\"" + pageSize_search + "\" name=\"pg.size\" /> ");
		return str.toString();
	}
	
	
	//数字风格的分页
	//curPage：当前页，pageSize_search：每页多少条，totalRow：信息总数，totalPage：总页数
	public String getNumberPageBar(){
		StringBuffer str = new StringBuffer("");
		int next, prev;
		prev = curPage - 1;
		next = curPage + 1;
		
		
		str.append("<style>");
		str.append(".other_pagenum{border:1px solid #A5AAAE;background:#FFFFFB;color:#5E8AA7;padding:1px 5px;vertical-align:middle;cursor:pointer;}");
		str.append(".this_pagenum{border:1px solid #A5AAAE;background:#628DAD;color:white;padding:1px 5px;vertical-align:middle;cursor:pointer;}");
		str.append(".xiu_button{padding:1px 5px;margin:5px;vertical-align:middle;cursor:pointer;border-top:1px solid #F9F9F9;border-left:1px solid #F9F9F9;border-bottom:1px solid #666666;border-right:1px solid #666666;}");
		str.append("</style>");
		
		if (curPage > 1) {
			str.append("<input type=\"button\" class=\"other_pagenum\" onclick=\"this.form['pg.curPage'].value=" + prev + ";this.form.submit();\" value=\"上页\" />&nbsp;");
		}
		
		
		int pageStart,pageEnd;
		
		int displayNum = 6;
		
		if(curPage < displayNum){
			pageStart = 1;
			if(totalPage < displayNum){
				pageEnd = totalPage;
			}else{
				pageEnd = displayNum;
			}
		}else{
			if(curPage == displayNum){
				pageStart = 1;
			}else{
				pageStart = curPage - displayNum;
			}
			pageEnd = curPage + displayNum;
			if(pageEnd > totalPage){
				pageEnd = totalPage;
			}
		}
		
		if(totalPage > 1){
		
			for(int i = pageStart; i <= pageEnd; i++){
				String thisnum = "other_pagenum";
				if(i == curPage){
					thisnum = "this_pagenum";
				}
				str.append("<input type=\"button\" class=\""+thisnum+"\" onclick=\"this.form['pg.curPage'].value=" + i + ";this.form.submit();\" value=\""+i+"\" />&nbsp;");
			}
		}
		
		
		if (curPage < totalPage) {
			str.append("<input type=\"button\" class=\"other_pagenum\" onclick=\"this.form['pg.curPage'].value=" + next + ";this.form.submit();\" value=\"下页\" />&nbsp;");
		}
		
		//str.append(" 共" + totalRow + "条记录");
		
		//str.append(" 共" + totalPage + "页");
		
		if(totalPage > 1){
		
			//str.append("<input type=\"hidden\"  name=\"pagesize\" value=\"20\" />");
			
			//str.append(" 到&nbsp;<input size=\"4\" maxlength=\"4\" name=\"pg.curPage\" value=\""+curPage+"\" style=\"vertical-align:middle;\"/>&nbsp;页");
			
			str.append(" <input name=\"pg.curPage\" value=\""+curPage+"\" type=\"hidden\"/>");
			//str.append("<input type=\"button\" class=\"xiu_button\" name=\"gosearch\" value=\"确定\" onclick='this.form.submit();' />");
			
			//str.append("<INPUT type=\"hidden\"  value=\"" + curPage + "\" name=\"pages_search\" /> ");
			str.append("<INPUT type=\"hidden\"  value=\"" + pageSize_search + "\" name=\"pg.size\" /> ");
		}
		
		return str.toString();
	}
	
	
	
	
	
	

}
