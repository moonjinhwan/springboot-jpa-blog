package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	//컨트롤러에서 세션찾기
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String boardForm() {
		return "/board/saveForm";
	}
}
