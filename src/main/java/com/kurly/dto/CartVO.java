package com.kurly.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartVO {
	
	private String customer;
	private int pcode;
	private int pcount;
	private String isorder;
	private String pname;
	private int price;
	private String org_filename;

}
