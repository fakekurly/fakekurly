package com.board.service;

import com.board.dto.ProductVO;

public interface ProductService {

	// 상품 한 개의 모든 정보를 가져온다
	public ProductVO getProductDetail(int pcode) throws Exception;
}
