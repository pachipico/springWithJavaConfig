package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.CommentVO;

public interface CommentDAO {
	int insert(CommentVO cvo);
	List<CommentVO> selectList(Long bno);
	int selectCnt(Long bno);
	int update(CommentVO cvo);
	int delete(Long cno);
	int deleteAll(Long bno);
}
