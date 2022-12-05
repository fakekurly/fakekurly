package com.kurly.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.kurly.dto.MemberVO;
import com.kurly.dto.OrderVO;
@Service
public interface OrderService {
	public OrderVO goOrderSheet(@Param("pcode") int pcode, @Param("customer") String customer);

	public List<MemberVO> customerInfo(@Param("userid")String userid);
	
	public void goOrder(@Param("pcode") int pcode, @Param("customer") String customer);

	public void delOrderCart();
	
	public void updateStock(@Param("customer") String customer);

	public void insertOrder();

	public int checkOrder(@Param("customer") String customer);

	public List<OrderVO> getOrderList(@Param("customer") String customer);

}
