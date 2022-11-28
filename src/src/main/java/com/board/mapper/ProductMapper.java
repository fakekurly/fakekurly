package com.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.board.dto.ProductVO;

@Mapper
public interface ProductMapper {

	// 상품 한 개의 모든 정보를 가져온다
	public ProductVO getProductDetail(int pcode);
}
