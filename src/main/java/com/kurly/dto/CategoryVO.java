package com.kurly.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryVO {
	
	private int tier;
	private String cateName;
	private String cateCode;
	private String cateParent;
	
}
