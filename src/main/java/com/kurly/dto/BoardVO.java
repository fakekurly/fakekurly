package com.kurly.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardVO {

	private int seqno;
	private int pcode;
	private String userid;
	private String mwriter;
	private String mtitle;
	private String mregdate;
	private String mcontent;
	private int hitno;
	
}
