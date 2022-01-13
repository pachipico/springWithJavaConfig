package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberDAO {
	int insertMember(MemberVO mvo);

	MemberVO selectEmail(String email);

	int insertAuthInit(String email);

	List<AuthVO> selectAuths(String email);

	int updateLastLogin(String email);
	
}
