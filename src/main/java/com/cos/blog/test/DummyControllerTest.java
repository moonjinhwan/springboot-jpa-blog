package com.cos.blog.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저가 없습니다. id: "+id);
		});
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable){
		Page<User> page = userRepository.findAll(pageable);
		List<User> pageList = page.getContent();
		return pageList;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			return "삭제에 실패 했습니다.";
		}
		return "삭제되었습니다. "+id;
	}
	
	@Transactional 
	@PutMapping("/dummy/user/{id}")
	public void updateUser(@PathVariable int id, @RequestBody User requestUser) {
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("유저가 없습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
	}
	
}
