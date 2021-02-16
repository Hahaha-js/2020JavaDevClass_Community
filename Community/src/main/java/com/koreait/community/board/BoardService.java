package com.koreait.community.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.community.SecurityUtils;
import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardDomain;
import com.koreait.community.model.BoardEntity;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private SecurityUtils sUtils;
	
	public int insBoard(BoardEntity p) {
		return mapper.insBoard(p);
	}
	
	public int selMaxPageNum(BoardDTO p) {
		return mapper.selMaxPageNum(p);
	}
	
	public List<BoardDomain> selBoardList(BoardDTO p) {
		int sIdx = (p.getPage() - 1) * p.getRowCnt();
		p.setsIdx(sIdx);		
		return mapper.selBoardList(p);
	}
	
	public BoardDomain selBoard(BoardDTO p) {
		return mapper.selBoard(p);
	}
	
	public BoardDomain selBoardWithHits(BoardDTO p, HttpSession hs) {
		//로그인 한 사람만 조회수 올릴꺼임!!
		if(sUtils.getLoginUser(hs) != null) {
			BoardEntity p2 = new BoardEntity();
			p2.setBoardPk(p.getBoardPk());
			p2.setHits(1);			
			mapper.updBoard(p2);
		}
		return selBoard(p);
	}
	
	public int delBoard(BoardEntity p) {
		p.setIsDel(1);
		return updBoard(p);
	}
	
	public int updBoard(BoardEntity p) {
		return mapper.updBoard(p);
	}
}











