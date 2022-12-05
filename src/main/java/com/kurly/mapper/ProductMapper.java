package com.kurly.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kurly.dto.ProductVO;

@Mapper
public interface ProductMapper {

	// 상품 한 개의 모든 정보를 가져온다
	public ProductVO getProductDetail(int pcode);
	
	// 재고가 5개 미만인 상품의 정보를 가져온다
	public List<ProductVO> getSoonSoldOut();
}
