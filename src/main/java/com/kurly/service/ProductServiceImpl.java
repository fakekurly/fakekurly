package com.kurly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kurly.dto.ProductVO;
import com.kurly.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductMapper mapper;
	
	// 상품 한 개의 모든 정보를 가져온다
	public ProductVO getProductDetail(int pcode) throws Exception {
		return mapper.getProductDetail(pcode);		
	}
	
	// 재고가 5개 미만인 상품의 정보를 가져온다
	public List<ProductVO> getSoonSoldOut() throws Exception {
		return mapper.getSoonSoldOut();
	}
	
	
	//상품 목록 전체 가져오기
	@Override
	public List<ProductVO> allProductList() throws Exception{;
	
		return mapper.allProductList();
	}
	
	//카테고리에 종류에 맞는 상품 목록 가져오기
	@Override
	public List<ProductVO> categoryProduct(Map<String,Object> data) throws Exception{
		
		return mapper.categoryProduct(data);
	};

	//카데고리코드에 맞는 메인카테고리 정보 가져오기
	@Override
	public CategoryVO mainCategory(String category) throws Exception{
		
		return mapper.mainCategory(category);
	};
	
	//카테고리에 맞는 상품의 브랜드 가져오기
	@Override
	public List<BrandVO> companyFilter(Map<String,Object> data) throws Exception{
		
		return mapper.companyFilter(data);
	}; 
	
	//상품 개수 세기
	@Override
	public int countProduct(Map<String,Object> data) throws Exception {;
	
		return mapper.countProduct(data);
	}
	
	//신상품
	//신상품 목록
	@Override
	public List<ProductVO> newProduct(Map<String,Object> data) throws Exception{
		
		return mapper.newProduct(data);
	};
	
	//신상품 개수 세기
	@Override
	public int countNewProduct(Map<String,Object> data) throws Exception{
		
		return mapper.countNewProduct(data);
	};
	
	//메인 카테고리 목록 가져오기
	@Override
	public List<CategoryVO> categoryFilter() throws Exception{
		
		return mapper.categoryFilter();
	};
	
}
