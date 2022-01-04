package com.myweb.www.repository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.myweb.www.domain.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	private SqlSession sql;
	
	private final String NS = "com.myweb.www.BoardMapper.";
	
	@Override
	public int insert(BoardVO bvo) {
		return sql.insert(NS+"reg", bvo);
	}

	@Override
	public BoardVO selectOne(Long bno) {
		
		return sql.selectOne(NS+"one" , bno);
	}

	@Override
	public List<BoardVO> selectList() {
		
		return sql.selectList(NS + "list");
	}

	@Override
	public int update(BoardVO bvo) {

		return sql.update(NS + "mod", bvo);
	}

	@Override
	public int updateReadCnt(Long bno) {

		return sql.update(NS + "readCntUp", bno);
	}

	@Override
	public int delete(Long bno) {

		return sql.delete(NS + "del", bno);
	}

}
