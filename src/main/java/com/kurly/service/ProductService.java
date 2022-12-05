package com.kurly.service;

import java.util.List;

import com.kurly.dto.ProductVO;

public interface ProductService {

	// 상품 한 개의 모든 정보를 가져온다
	public ProductVO getProductDetail(int pcode) throws Exception;
	
	// 재고가 5개 미만인 상품의 정보를 가져온다
	public List<ProductVO> getSoonSoldOut() throws Exception;
}
