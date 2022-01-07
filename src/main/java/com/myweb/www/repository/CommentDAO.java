package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;

public interface CommentDAO {
	int insertComment(CommentVO cvo);
	List<CommentVO> selectListComment(@Param("pno") Long pno,@Param("pgvo") PagingVO pgvo);
	int selectCntComment(Long pno);
	int updateComment(CommentVO cvo);
	int deleteComment(Long cno);
	int deleteAllComment(Long pno);
}
