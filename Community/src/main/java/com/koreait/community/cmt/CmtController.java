package com.koreait.community.cmt;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.community.model.CmtEntity;

@RequestMapping("/cmt")
@RestController
public class CmtController {
	
	@Autowired
	private CmtService service;
		
	@GetMapping
	public List<CmtEntity> list (CmtEntity p) {		
		return service.selCmtList(p);
	}
	
	@PostMapping
	public int ins(CmtEntity p, HttpSession hs) {
		p.setWriterPk(2);
		return service.insCmt(p);
	}
}












