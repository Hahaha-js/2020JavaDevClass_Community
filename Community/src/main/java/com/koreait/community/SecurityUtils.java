package com.koreait.community;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
	public String getSalt() {
		return BCrypt.gensalt();
	}
	
	public String getHashPw(String pw, String salt) {
		return BCrypt.hashpw(pw, salt);
	}
}
