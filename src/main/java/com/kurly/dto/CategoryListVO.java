package com.kurly.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryListVO {
	
	private String category;
	private int countCategoryProduct;
	private String cateName;
}
