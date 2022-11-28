package com.kurly.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikeVO {
	
	private int pcode;
	private String userid;
	private String mylikecheck;
	private String likedate;

}
