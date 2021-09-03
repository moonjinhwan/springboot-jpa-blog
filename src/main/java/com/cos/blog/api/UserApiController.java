package com.cos.blog.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession httpSession;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> join(@RequestBody User user){
		//실제 DB에 인서트하기.
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<>(HttpStatus.OK.value(), 1);
	}
	
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user){
		User principle = userService.로그인(user);
		if(principle != null) {
			System.out.println("로그인 완료");
			httpSession.setAttribute("principle", principle);
		}
		return new ResponseDto<>(HttpStatus.OK.value(), 1);
	}
}