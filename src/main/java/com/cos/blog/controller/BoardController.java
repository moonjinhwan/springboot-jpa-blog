package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//컨트롤러에서 세션찾기
	@GetMapping("/")
	public String index(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size=3) Pageable pageable) {
		model.addAttribute("boards", boardService.글목록(pageable));
		return "index";
	}
	
	@GetMapping("/board/saveForm")
	public String boardForm() {
		return "/board/saveForm";
	}
	
	@GetMapping("/board/{id}")
	public String detailForm(@PathVariable int id ,Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "/board/detail";
	}
	
	@GetMapping("/board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "/board/updateForm";
	}
}
