package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO {
	private int pageNo; // 현재 화면 페이지 번호
	private int qty; // 한 페이지당 보여줄 페이지네이션 번호, 한개의 페이지에서 보여줄 글 수
	
	private String type;
	private String keyword;
	
	public PagingVO() {
		this(1, 10);
	}

	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}

	public int getPageStart() {
		return (this.pageNo - 1) * qty;
	}
	
	public String[] getTypeToArr() {
		return type == null ? new String[] {} : type.split("");
	}

}
