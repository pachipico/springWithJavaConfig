package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.BCommentVO;
import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;

public interface BCommentDAO {
	int insertBComment(CommentVO cvo);

	List<CommentVO> selectListBComment(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);

	int selectOneBCommentTotalCount(long bno);

	int updateBComment(CommentVO cvo);

	int deleteOneBComment(long cno);

	int deleteAllBComment(long bno);
}
