package com.kurly.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.kurly.dto.CartVO;

@Service
public interface CartService {

	public int isInCart(String customer, int pcode);
	
	public void updateCart(String customer,int pcode,int pcount);

	public void insertCart(String customer, int pcode, int pcount);
	
	public int checkCart(String customer);
	
	public List<CartVO> getCartList(String customer);
	
	public void deleteAll(@Param("customer")String customer);
	
	public void modifyCount(int pcount, int pcode, String customer);

	public void deleteThis(int pcode,String customer);
	
	public void deleteCheck(int pcode,String customer );


}
