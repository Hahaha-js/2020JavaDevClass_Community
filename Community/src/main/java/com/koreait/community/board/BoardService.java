package com.koreait.community.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.koreait.community.model.BoardEntity;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	public int insBoard(BoardEntity p) {
		return mapper.insBoard(p);
	}
}
