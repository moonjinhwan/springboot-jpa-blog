package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;




@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)//DB의 연결된 넘버링전략 따라감
	private int id;
	@Column(nullable = false, length = 30)
	private String username;
	@Column(nullable = false, length = 100)
	private String password;
	@Column(nullable = false, length = 50)
	private String email;
	@CreationTimestamp
	private Timestamp createDate;
	@ColumnDefault("'user'")
	private String role;
}
