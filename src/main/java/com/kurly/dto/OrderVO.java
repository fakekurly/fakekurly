package com.kurly.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderVO {

	private int order_code;
	private String customer;
	private int pcode;
	private int pcount;
	private String pname;
    private int price;
    private String org_filename;
	private String state;
	private String order_date;
	private String address;
	
}
