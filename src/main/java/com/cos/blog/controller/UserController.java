package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController {
	
	@Value("${oauth.key}")
	private String oauthKey;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
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
	public String kakaoCode(String code){
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
				HttpMethod.POST, 
				kakaoTokenRequest, 
				String.class
		);
		
		ObjectMapper objectMapper  = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//access token으로 email받아오기
		RestTemplate rt2 = new RestTemplate();
		//헤더
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Content-Type", "application/x-www-form-urlencoded");
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		//헤더 합치기
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2  = 
				new HttpEntity<>(headers2);
		//리스폰스에 넣기
		ResponseEntity<String> response2 =rt2.exchange(
				"https://kapi.kakao.com/v2/user/me" , 
				HttpMethod.POST, 
				kakaoProfileRequest2, 
				String.class
		);
		//프로필 생성
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null; 
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		System.out.println("카카오 아이디(번호) : "+kakaoProfile.getId());
		System.out.println("카카오 이메일 : "+kakaoProfile.getKakao_account().getEmail());
		
		//객체 생성
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(oauthKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		//같은 사용자이름이 있으면 회원가입 못함
		User originUser = userService.회원찾기(kakaoUser.getUsername()); 
		if(originUser.getUsername() == null) {
			userService.회원가입(kakaoUser);
		}
		//로그인처리- 세션에 저장
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kakaoUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}
}
