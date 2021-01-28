package com.koreait.community.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.community.Const;
import com.koreait.community.SecurityUtils;
import com.koreait.community.model.UserEntity;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private SecurityUtils sUtils;
	
	//1:회원가입성공, 0: 회원가입실패
	public int join(UserEntity p) {
		if(p.getUserId() == null || p.getUserId().length() < 2) {
			return 0;
		}
		//비밀번호 암호화
		String salt = sUtils.getSalt();
		String hashPw = sUtils.getHashPw(p.getUserPw(), salt);		
		p.setSalt(salt);
		p.setUserPw(hashPw);
		
		return mapper.insUser(p);
	}
	
	//1:로그인 성공, 2:아이디 없음, 3:비밀번호가 틀림
	public int login(UserEntity p, HttpSession hs) {
		UserEntity dbData = mapper.selUser(p);
		if(dbData == null) {
			return 2;
		}
		
		String salt = dbData.getSalt();
		String cryptPw = sUtils.getHashPw(p.getUserPw(), salt);
		if(!cryptPw.equals(dbData.getUserPw())) {
			return 3;
		}
		dbData.setUserPw(null);
		dbData.setSalt(null);
		dbData.setRegDt(null);
		hs.setAttribute(Const.KEY_LOGINUSER, dbData);
		return 1;
	}
	
	public int chkId(UserEntity p) {
		UserEntity dbData = mapper.selUser(p);
		if(dbData == null) { //아이디 없음
			return 0;
		}
		return 1;		//아이디 있음
	}
}













