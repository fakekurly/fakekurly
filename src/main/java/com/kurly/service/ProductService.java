package com.kurly.service;

import java.util.List;

import com.kurly.dto.ProductVO;

public interface ProductService {

	// 상품 한 개의 모든 정보를 가져온다
	public ProductVO getProductDetail(int pcode) throws Exception;
		
	// 재고가 5개 미만인 상품의 정보를 가져온다
	public List<ProductVO> getSoonSoldOut() throws Exception;
	
	//카데고리 전체 목록 가져오기
	public List<CategoryVO> allCategory() throws Exception;
	
	//카테고리에 종류에 맞는 상품 목록 가져오기
	public List<ProductVO> categoryProduct(Map<String,Object> data) throws Exception;
	
	//카테고리코드에 맞는 메인카테고리 정보 가져오기
	public CategoryVO mainCategory(String category) throws Exception;
	
	//카테고리코드에 맞는 서브카테고리 정보 가져오기
	public List<CategoryVO> subCategory(String category) throws Exception;
	
	//카테고리에 맞는 상품의 브랜드 가져오기
	public List<BrandVO> companyFilter(Map<String,Object> data) throws Exception; 
	
	//상품 개수 세기
	public int countProduct(Map<String,Object> data) throws Exception;
	
	//신상품
	//신상품 목록
	public List<ProductVO> newProduct(Map<String,Object> data) throws Exception;
	
	//신상품 개수 세기
	public int countNewProduct(Map<String,Object> data) throws Exception;
	
	//메인 카테고리 목록 가져오기
	public List<CategoryVO> categoryFilter() throws Exception;
	
	//알뜰상품
	//알뜰상품 목록
	public List<ProductVO> saleProduct(Map<String,Object> data) throws Exception;
	
	//알뜰상품 개수 세기
	public int countSaleProduct(Map<String,Object> data) throws Exception;
	
	//메인 카테고리 목록 가져오기
	public List<CategoryVO> categoryFilterSale() throws Exception;
	
	//베스트상품
	//베스트상품 목록
	public List<ProductVO> bestProduct(Map<String,Object> data) throws Exception;
	
	//베스트 개수 세기
	public int countBestProduct(Map<String,Object> data) throws Exception;
	
	//베스트 카테고리 목록 가져오기
	public List<CategoryVO> categoryFilterBest() throws Exception;
	
	//상품 찾기
	//상품 찾기 목록
	public List<ProductVO> searchProduct(Map<String,Object> data) throws Exception;
	
	//상품 찾기 개수 세기
	public int countSearchProduct(Map<String,Object> data) throws Exception;
	
	//상품 찾기 카테고리 목록 가져오기
	public List<CategoryVO> categoryFilterSearch(Map<String,Object> data) throws Exception;
	 
	
}
