package com.myweb.www.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
@Data // 불필요한 생성자도 생성됨.
public class BoardVO {

	private long bno;
	private String title;
	private String content;
	private String writer;
	private String regAt;
	private String modAt;
	private int readCount;
	private int hasFile;
	private int cmtQty;

}
