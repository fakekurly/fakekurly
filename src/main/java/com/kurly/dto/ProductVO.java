package com.kurly.dto;

import java.time.LocalDateTime;

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
	private String image;
	private String org_filename;
	private String stored_filename;
	private int order_quantity;
	private	LocalDateTime pregdate;
	private long discount;
	private String thisweerk;
}
