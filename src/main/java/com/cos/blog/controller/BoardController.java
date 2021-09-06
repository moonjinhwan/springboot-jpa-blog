package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	//컨트롤러에서 세션찾기
	@GetMapping("/")
	public String index(@AuthenticationPrincipal PrincipalDetail principalDetail) {
		System.out.println("로그인 ID: "+principalDetail.getUsername());
		return "index";
	}
}
