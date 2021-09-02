package com.cos.blog.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.User;

@RestController
public class UserApiController {
	@PostMapping("/api/user")
	public ResponseDto<Integer> join(@RequestBody User user){
		//실제 DB에 인서트하기.
		return new ResponseDto<>(HttpStatus.OK, 1);
	}
}