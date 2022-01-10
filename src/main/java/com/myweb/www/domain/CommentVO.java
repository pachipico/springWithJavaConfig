package com.myweb.www.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
@ToString
public class CommentVO {
	private Long cno;
	private Long bno;
	private Long pno;
	private String writer;
	private String content;
	private String regAt;
	private String modAt;
	
}
