package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		String rawPass = user.getPassword();
		String encPass = encoder.encode(rawPass);
		user.setPassword(encPass);
		userRepository.save(user);
	}
	
	@Transactional
	public void 회원수정(User requestUser) {
		User user  = userRepository.findById(requestUser.getId())
				.orElseThrow(()->{
					return new IllegalArgumentException("회원이 없습니다.");
				});
		user.setEmail(requestUser.getEmail());
		String decodePwd=requestUser.getPassword();
		String encodePwd=encoder.encode(decodePwd);
		user.setPassword(encodePwd);
	}
	
	@Transactional
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
}

//@Transactional(readOnly = true)
//public User 로그인(User user) {
//	return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//}