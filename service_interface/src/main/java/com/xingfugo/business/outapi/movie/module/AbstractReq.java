package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public abstract class AbstractReq {
	
	@XStreamAlias("head")
	private ReqHeader head;
	
	public ReqHeader getHead() {
		return head;
	}

	public void setHead(ReqHeader head) {
		this.head = head;
	}
}
