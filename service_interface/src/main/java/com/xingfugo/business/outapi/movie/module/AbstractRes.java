package com.xingfugo.business.outapi.movie.module;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public abstract class AbstractRes {
	
	@XStreamAlias("head")
	private ResHeader head;
	
	public ResHeader getHead() {
		return head;
	}

	public void setHead(ResHeader head) {
		this.head = head;
	}
}
