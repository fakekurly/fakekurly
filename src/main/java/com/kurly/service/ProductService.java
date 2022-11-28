package com.kurly.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kurly.dto.ProductVO;

@Service
public interface ProductService {
	
	public ProductVO productdetail(int pcode) throws Exception;

}
