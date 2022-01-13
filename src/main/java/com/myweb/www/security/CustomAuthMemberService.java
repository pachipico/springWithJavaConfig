package com.myweb.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myweb.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthMemberService implements UserDetailsService {

	@Inject
	private MemberDAO mdao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		MemberVO mvo = mdao.selectEmail(email);
		mvo.setAuthList(mdao.selectAuths(email));
		return new AuthMember(mvo);
	}

}
