package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BCommentVO;

public interface BCommentDAO {
	int insertBComment(BCommentVO cvo);
	List<BCommentVO> selectListBComment(long pno);
	int updateBComment(BCommentVO cvo);
	int deleteOneBComment(long cno);
	int deleteAllBComment(long pno);
}
