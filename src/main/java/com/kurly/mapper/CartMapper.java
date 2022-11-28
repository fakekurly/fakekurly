package com.kurly.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kurly.dto.CartVO;

@Mapper
public interface CartMapper {

	public int isInCart(@Param("customer")String customer, @Param("pcode")int pcode);
	
	public void updateCart(@Param("customer")String customer, @Param("pcode")int pcode, @Param("pcount")int pcount);
	
	public void insertCart(@Param("customer")String customer, @Param("pcode")int pcode, @Param("pcount")int pcount);
	
	public int checkCart(@Param("customer")String customer);
	
	public List<CartVO> getCartList(@Param("customer") String customer);
	
	public void deleteAll(@Param("customer")String customer);
	
	public void modifyCount(@Param("pcount") int pcount, @Param("pcode") int pcode, @Param("customer") String customer);
	
	public void deleteThis(@Param("pcode") int pcode, @Param("customer") String customer);
	
	public void deleteCheck(@Param("pcode") int pcode,@Param("customer") String customer );
}
