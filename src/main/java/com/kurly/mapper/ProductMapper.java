package com.kurly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kurly.dto.ProductVO;

@Mapper
public interface ProductMapper {
	
	public ProductVO productdetail(int pcode) throws Exception;

}
