package com.cos.blog.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	@GetMapping("/auth/kakao/callback")
	public @ResponseBody String kakaoCode(String code){
		RestTemplate rt = new RestTemplate();
		//헤더
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		//바디
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "a5bba68f4cc2a6ad3516d39e67df2cae");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		//바디와 헤더 합치기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params, headers);
		//응답에 담아서 보내기
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token" , 
				HttpMethod.POST, kakaoTokenRequest, 
				String.class);
		return "응답을 받았습니다: "+response;
	}
}
