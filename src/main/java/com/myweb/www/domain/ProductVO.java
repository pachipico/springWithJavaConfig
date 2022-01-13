package com.myweb.www.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductVO {
	private Long pno;
	private String category;
	private String pname;
	private int price;
	private String writer;
	private String description;
	private String madeBy;
	private String regAt;
	private String modAt;
	private int readCount;
	private int cmtQty;
	private int hasFile;
	
	
}
