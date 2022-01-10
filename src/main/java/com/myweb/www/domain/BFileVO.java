package com.myweb.www.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BFileVO {
	private String uuid;
	private String saveDir;
	private String fileName;
	private int fileType;
	private Long bno;
	private Long fileSize;
	private String regAt;
}
