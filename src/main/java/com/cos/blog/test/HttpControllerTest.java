package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {
	@GetMapping("/http/get")
	public String httpGet() {
		return "get요청";
	}
	
	@PostMapping("/http/post")
	public String httpPost() {
		return "post요청";
	}
	
	@PutMapping("/http/put")
	public String httpPut() {
		return "put요청";
	}
	
	@DeleteMapping("/http/delete")
	public String httpDelete() {
		return "delete요청";
	}
}
