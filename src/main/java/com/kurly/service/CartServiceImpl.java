package com.kurly.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurly.dto.CartVO;
import com.kurly.mapper.CartMapper;


@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartMapper mapper; 
	
	
	public int isInCart(String customer, int pcode){
		return mapper.isInCart(customer, pcode);
	}


	@Override
	public void updateCart(String customer, int pcode, int pcount) {
		// TODO Auto-generated method stub
		mapper.updateCart(customer, pcode, pcount);
	}


	@Override
	public void insertCart(String customer, int pcode, int pcount) {
		// TODO Auto-generated method stub
		mapper.insertCart(customer, pcode, pcount);
	}


	@Override
	public int checkCart(String customer) {
		// TODO Auto-generated method stub
		return mapper.checkCart(customer);
	}


	@Override
	public List<CartVO> getCartList(String customer) {
		// TODO Auto-generated method stub
		return mapper.getCartList(customer);
	}


	@Override
	public void deleteAll(String customer) {
		// TODO Auto-generated method stub
		mapper.deleteAll(customer);
	}


	@Override
	public void modifyCount(int pcount, int pcode, String customer) {
		// TODO Auto-generated method stub
		mapper.modifyCount(pcount, pcode, customer);
	}


	@Override
	public void deleteThis(int pcode, String customer) {
		// TODO Auto-generated method stub
		mapper.deleteThis(pcode, customer);
	}


	@Override
	public void deleteCheck(int pcode, String customer) {
		// TODO Auto-generated method stub
		mapper.deleteCheck(pcode, customer);
	}

}
