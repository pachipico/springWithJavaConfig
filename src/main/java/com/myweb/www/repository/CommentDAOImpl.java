package com.myweb.www.repository;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.myweb.www.domain.CommentVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommentDAOImpl implements CommentDAO {

	@Inject
	private SqlSession sql;

	private final String NS = "com.myweb.www.CommentMapper.";

	@Override
	public int insert(CommentVO cvo) {

		return sql.insert(NS + "reg", cvo);
	}

	@Override
	public List<CommentVO> selectList(Long bno) {

		return sql.selectList(NS + "list", bno);
	}

	@Override
	public int update(CommentVO cvo) {

		return sql.update(NS + "mod", cvo);
	}

	@Override
	public int delete(Long cno) {

		return sql.delete(NS + "del", cno);
	}

	@Override
	public int deleteAll(Long bno) {

		return sql.delete(NS + "delAll", bno);
	}

	@Override
	public int selectCnt(Long bno) {
		
		return sql.selectOne(NS+"cnt", bno);
	}

}
