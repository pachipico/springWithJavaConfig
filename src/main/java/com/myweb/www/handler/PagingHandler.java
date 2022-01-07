package com.myweb.www.handler;

import java.util.List;

import com.myweb.www.domain.BCommentVO;
import com.myweb.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class PagingHandler {
	private int startPage; // 화면에서 보여줄 시작페이지 번호
	private int endPage; // 화면에서 보여줄 마지막페이지 번호
	private boolean prev, next;

	private int totalCount;
	private PagingVO pgvo;

	private List<BCommentVO> cmtList;

	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.totalCount = totalCount;
		this.pgvo = pgvo;

		endPage = (int) (Math.ceil(pgvo.getPageNo() / (pgvo.getQty() * 1.0))) * pgvo.getQty();
		startPage = endPage - 9;
		int realEndPage = (int) Math.ceil((totalCount * 1.0) / pgvo.getQty());
		if (realEndPage < endPage) {
			endPage = realEndPage;
		}
		prev = startPage > 1;
		next = endPage < realEndPage;
	}

	public PagingHandler(PagingVO pgvo, int totalCount, List<BCommentVO> cmtList) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}

}
