package com.myweb.www.ctrl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.PagingVO;
import com.myweb.www.domain.ProductVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Inject 
	ProductService psv;
	
	@GetMapping("/list")
 	public void list(Model model, PagingVO pgvo ) {
		
		model.addAttribute("list", psv.getList(pgvo));
		int totalCount = psv.getCount(pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
		
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(Model model,@ModelAttribute("pgvo") PagingVO pgvo ,@RequestParam("pno") Long pno, RedirectAttributes reAttr) {
		log.debug("detail pgvo ============ {}", pgvo);
		model.addAttribute("pvo", psv.getDetail(pno));
	}
	
	@PostMapping("/modify")
	public String modify(ProductVO pvo, @ModelAttribute("pgvo") PagingVO pgvo , RedirectAttributes reAttr) {
		reAttr.addFlashAttribute("isUp", psv.modify(pvo));
		return "redirect:/product/detail?pno="+ pvo.getPno();
	}
	
	
}
