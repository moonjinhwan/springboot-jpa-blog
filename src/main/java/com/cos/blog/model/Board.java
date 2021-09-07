package com.cos.blog.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	private String title;
	
	@Lob
	private String content;
	
	private int count;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	//외래키가 아니다. select문을 위해서 존재하는것.
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
	private List<Reply> reply;
	
	@ManyToOne//기본 전략이 EAGER 임.
	@JoinColumn(name = "userId")
	private User user;
}
