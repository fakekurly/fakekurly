package com.kurly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurly.dto.MemberVO;
import com.kurly.dto.OrderVO;
import com.kurly.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper mapper; 
	@Override
	public OrderVO goOrderSheet(int pcode, String customer) {
		// TODO Auto-generated method stub
		return mapper.goOrderSheet(pcode, customer);
	}
	@Override
	public List<MemberVO> customerInfo(String userid) {
		// TODO Auto-generated method stub
		return mapper.customerInfo(userid);
	}
	
	@Override
	public void goOrder(int pcode, String customer) {
		// TODO Auto-generated method stub
		mapper.goOrder(pcode, customer);
	}
	@Override
	public void delOrderCart() {
		// TODO Auto-generated method stub
		mapper.delOrderCart();
	}
	@Override
	public void updateStock(String customer) {
		// TODO Auto-generated method stub
		mapper.updateStock(customer);
	}
	@Override
	public void insertOrder() {
		// TODO Auto-generated method stub
		mapper.insertOrder();
	}
	@Override
	public int checkOrder(String customer) {
		// TODO Auto-generated method stub
		return mapper.checkOrder(customer);
	}
	@Override
	public List<OrderVO> getOrderList(String customer) {
		// TODO Auto-generated method stub
		return mapper.getOrderList(customer);
	}

}
