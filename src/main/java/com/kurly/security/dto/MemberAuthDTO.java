package com.kurly.security.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class MemberAuthDTO extends User{

	private static final long serialVersionUID = 1L;

	// 생성자
	public MemberAuthDTO(String username,String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// username -> userid
	}

}
