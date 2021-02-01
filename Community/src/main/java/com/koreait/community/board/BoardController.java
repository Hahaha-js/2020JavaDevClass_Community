package com.koreait.community.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.community.SecurityUtils;
import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardEntity;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private SecurityUtils sUtils;
	
	@GetMapping("/home")
	public void home() {	}
	
	@GetMapping("/list")
	public void list(BoardDTO p, Model model) {
	}
	
	@GetMapping("/write")
	public String write() {
		return "board/writeEdit";
	}
	
	@PostMapping("/write")
	public String	 write(BoardEntity p, HttpSession hs) {		
		p.setUserPk(sUtils.getUserPk(hs));
		int result = service.insBoard(p);
		return "redirect:/board/detail?boardPk=" + p.getBoardPk();
	}
}










