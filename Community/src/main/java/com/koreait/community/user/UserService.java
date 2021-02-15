package com.koreait.community.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.community.Const;
import com.koreait.community.FileUtils;
import com.koreait.community.SecurityUtils;
import com.koreait.community.model.UserEntity;

@Service
public class UserService {
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private SecurityUtils sUtils;
	
	@Autowired
	private FileUtils fUtils;
		
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
		dbData.setProfileImg(null);
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
	
	public UserEntity selUser(UserEntity p) {
		return mapper.selUser(p);
	}
	
	public int uploadProfile(MultipartFile mf, HttpSession hs) {
		int userPk = sUtils.getLoginUserPk(hs);
		if(userPk == 0 || mf == null) { //로그인이 안 되어 있는 경우, 파일이 없는 경우
			return 0;
		}
		String folder = "/res/img/user/" + userPk;		
		String profileImg = fUtils.transferTo(mf, folder);
		if(profileImg == null) { //파일 생성 실패
			return 0;
		}
		
		UserEntity p = new UserEntity();
		p.setUserPk(userPk);
		
		//기존이미지가 존재했다면 이미지 삭제!
		UserEntity userInfo = mapper.selUser(p);
		if(userInfo.getProfileImg() != null) {
			String basePath = fUtils.getBasePath(folder); 
			File file = new File(basePath, userInfo.getProfileImg());
			if(file.exists()) {
				file.delete();
			}
		}
				
		p.setProfileImg(profileImg);
		return mapper.updUser(p);
	}
}













