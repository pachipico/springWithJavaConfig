package com.myweb.www.ctrl;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Inject
	private BCryptPasswordEncoder bcpEncoder;
	
	@Inject
	private MemberService msv;
	
	@GetMapping("/register")
	public void register() {
	}
	
	@GetMapping("/login")
	public void login() {}
	
	@PostMapping("/register")
	public String register(MemberVO mvo, RedirectAttributes reAttr) {
	
		mvo.setPwd(bcpEncoder.encode(mvo.getPwd()));
		
		return "redirect:" + (msv.register(mvo) > 0 ? "/member/register" : "/member/register");
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes reAttr) {
		
		reAttr.addFlashAttribute("email", request.getAttribute("email"));
		reAttr.addFlashAttribute("errMsg", request.getAttribute("errMsg"));
		return "redirect:/member/login";
	}
}
