package com.board.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductVO {

	private int pcode;
	private String pname;
	private String company;
	private int price;
	private int stock;
	private String description;
	private String postscript;
	private String category;
	private String org_filename;
	private String stored_filename;	
}
