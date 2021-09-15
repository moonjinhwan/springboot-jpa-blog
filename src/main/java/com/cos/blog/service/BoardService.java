package com.cos.blog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private ReplyRepository replyRepository;
	@Transactional
	public void 글쓰기(Board board, User user) {
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다. "+id);
		});
	}
	
	@Transactional
	public void 글삭제(int id) {
		System.out.println("글삭제하기: "+id);
		boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정(int id, Board requestBoard) {
		System.out.println("글 수정하기: "+id);
		Board board = boardRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 수정 실패: 아이디를 찾을 수 없습니다. "+id);
		});
		board.setContent(requestBoard.getContent());
		board.setTitle(requestBoard.getTitle());
	}
	
	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply requestReply) {
		System.out.println("댓글쓰기:"+ boardId);
		Board board = boardRepository.findById(boardId).orElseThrow(()->{
			return new IllegalArgumentException("댓글 쓰기 실패: 아이디를 찾을 수 없습니다. "+boardId);
		});
		requestReply.setUser(user);
		requestReply.setBoard(board);
		replyRepository.save(requestReply);
	}
	
	@Transactional
	public void 댓글삭제(int replyId) {
		replyRepository.deleteById(replyId);		
	}
}
