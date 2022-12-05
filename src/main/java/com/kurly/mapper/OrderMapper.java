package com.kurly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kurly.dto.MemberVO;
import com.kurly.dto.OrderVO;

@Mapper
public interface OrderMapper {

	public OrderVO goOrderSheet(@Param("pcode") int pcode, @Param("customer") String customer);
	
	public List<MemberVO> customerInfo(@Param("userid")String userid);

	public void goOrder(@Param("pcode") int pcode, @Param("customer") String customer);
	
	public void delOrderCart();
	
	public void updateStock(@Param("customer") String customer);
	
	public void insertOrder();
	
	public int checkOrder(@Param("customer") String customer);
	
	public List<OrderVO> getOrderList(@Param("customer") String customer);
	
	public void updateOrderQuan();
}
