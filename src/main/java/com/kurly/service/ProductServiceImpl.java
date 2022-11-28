package com.kurly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurly.dto.ProductVO;
import com.kurly.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductMapper mapper;
	
	//상세페이지 보기
	@Override
	public ProductVO productdetail(int pcode) throws Exception{
		return mapper.productdetail(pcode);
	}
	
}
