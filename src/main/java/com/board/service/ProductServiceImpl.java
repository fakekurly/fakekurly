package com.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dto.ProductVO;
import com.board.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper mapper;
	
	// 상품 한 개의 모든 정보를 가져온다
	public ProductVO getProductDetail(int pcode) throws Exception {
		return mapper.getProductDetail(pcode);		
	}
}
