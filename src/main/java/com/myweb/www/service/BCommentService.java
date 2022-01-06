package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BCommentVO;

public interface BCommentService {
	int register(BCommentVO cvo);
	List<BCommentVO> getList(long pno);
	int modify(BCommentVO cvo);
	int remove(long cno);
}
