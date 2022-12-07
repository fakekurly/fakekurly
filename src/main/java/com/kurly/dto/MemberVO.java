package com.kurly.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberVO {
	
	private String userid;
	private String username;
	private String password;
	private String telno;
	private String email;
	private String zipcode;
	private String address;
	private LocalDateTime regdate;
	private LocalDateTime lastlogindate;
	private LocalDateTime lastpwdate;
	private int pwcheck;
	private String role;
	private String grade;
}
