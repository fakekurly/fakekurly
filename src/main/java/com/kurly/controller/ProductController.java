package com.kurly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurly.dto.ProductVO;
import com.kurly.service.ProductService;
import com.kurly.util.Page;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping("/main")
	public void GetIndex(Model model, HttpSession session) throws Exception {
		
		// 재고가 10개 미만인 상품을 전부 가져와서, 그 중 4개 항목만 선택하여 보여줌
		List<ProductVO> ssolist = service.getSoonSoldOut();
		List<ProductVO> ssoResult = new ArrayList<ProductVO>();
		for(int i = 0; i < 4; i++) {
			ssoResult.add(ssolist.get(i));
		}
		model.addAttribute("ssolist", ssoResult);
	}
	
	//상품 목록
	@RequestMapping("/collections/category_product")
	public void GetCategoryProductList(Model model,
						@RequestParam("category") String category,
						@RequestParam(name="sortType", required=false) String sortType,
						@RequestParam(value="checkedBrand", required=false) List<String> checkedBrand
													) throws Exception{
		
		Map<String, Object> data = new HashMap<>();
		data.put("category", category);
		data.put("sortType", sortType);
		data.put("checkedBrand", checkedBrand);
		
		model.addAttribute("mainCategory", service.mainCategory(category));
		model.addAttribute("companyFilter", service.companyFilter(data));
		model.addAttribute("countProduct", service.countProduct(data));
		model.addAttribute("categoryProduct", service.categoryProduct(data));
		
	}
	
	@ResponseBody
	@RequestMapping("/collections/filter")
	public Map<String, Object> GetCategoryProductListFilter(Model model, 
						@RequestParam("category") String category,
						@RequestParam(name="sortType", required=false) String sortType,
						@RequestParam(value="checkedBrand[]") List<String> checkedBrand
													) throws Exception{
	
		//log.info("카테고리 : " + category);
		//log.info("서브카테고리 : " + subCategory);
		//log.info("배열 확인 : " + checkedBrand);
		
		Map<String, Object> data = new HashMap<>();
		data.put("category", category);
		data.put("sortType", sortType);
		data.put("checkedBrand", checkedBrand);
		
		//log.info("데이터 :" + data); 
		
		 Map<String, Object> result = new HashMap<>();
		 result.put("category", service.mainCategory(category));
		 result.put("categoryProduct", service.categoryProduct(data));
	
		 return result;
	}
	
	//newProduct
	@RequestMapping("/collections/newProduct")
	public void GetnewProductList(Model model,
					@RequestParam(name="sortType", required=false) String sortType,
					@RequestParam(value="checkedCategory", required=false) List<String> checkedBrand
													) throws Exception{
		
		Map<String, Object> data = new HashMap<>();
		data.put("sortType", sortType);
		
		
		model.addAttribute("categoryFilter", service.categoryFilter());
		model.addAttribute("countNewProduct", service.countNewProduct(data));
		model.addAttribute("newProduct", service.newProduct(data));
		
	}
	
	@ResponseBody
	@RequestMapping("/collections/newProductfilter")
	public Map<String, Object> GetnewProductfilter(Model model, 
						 @RequestParam(name="sortType", required=false) String sortType,
						 @RequestParam(value="checkedCategory[]") List<String> checkedCategory
													) throws Exception{
	
		//log.info("배열 확인 : " + checkedCategory);
		
		Map<String, Object> data = new HashMap<>();
		data.put("sortType", sortType);
		data.put("checkedCategory", checkedCategory);
		
		//log.info("데이터 :" + data); 
		
		 Map<String, Object> result = new HashMap<>();
		 result.put("categoryProduct", service.newProduct(data));
	
		//log.info("결과 :" + result);
		 
		 return result;
	}
	
	//saleProduct
	@RequestMapping("/collections/saleProduct")
	public void GetSaleProductList(Model model,
					@RequestParam(name="sortType", required=false) String sortType,
					@RequestParam(value="checkedCategory", required=false) List<String> checkedBrand
													) throws Exception{
		
		Map<String, Object> data = new HashMap<>();
		data.put("sortType", sortType);
		
		
		model.addAttribute("categoryFilterSale", service.categoryFilterSale());
		model.addAttribute("countSaleProduct", service.countSaleProduct(data));
		model.addAttribute("saleProduct", service.saleProduct(data));
		
	}
	
	@ResponseBody
	@RequestMapping("/collections/saleProductfilter")
	public Map<String, Object> GetSaleProductfilter(Model model, 
						@RequestParam(name="sortType", required=false) String sortType,
						@RequestParam(value="checkedCategory[]") List<String> checkedCategory
													) throws Exception{
	
		//log.info("배열 확인 : " + checkedCategory);
		
		Map<String, Object> data = new HashMap<>();
		data.put("sortType", sortType);
		data.put("checkedCategory", checkedCategory);
		
		//log.info("데이터 :" + data); 
		
		 Map<String, Object> result = new HashMap<>();
		 result.put("categoryProduct", service.saleProduct(data));
	
		//log.info("결과 :" + result);
		 
		 return result;
	}
	
	//bestProduct
		@RequestMapping("/collections/bestProduct")
		public void GetBestProductList(Model model,
					 @RequestParam(name="sortType", required=false) String sortType,
					 @RequestParam(value="checkedCategory", required=false) List<String> checkedBrand
														) throws Exception{
			
			Map<String, Object> data = new HashMap<>();
			data.put("sortType", sortType);
			
			
			model.addAttribute("categoryFilterBest", service.categoryFilterBest());
			model.addAttribute("countBestProduct", service.countBestProduct(data));
			model.addAttribute("bestProduct", service.bestProduct(data));
			
		}
		
	@ResponseBody
	@RequestMapping("/collections/bestProductfilter")
	public Map<String, Object> GetBestProductfilter(Model model, 
						@RequestParam(name="sortType", required=false) String sortType,
						@RequestParam(value="checkedCategory[]") List<String> checkedCategory
														) throws Exception{
	
		//log.info("배열 확인 : " + checkedCategory);
			
		Map<String, Object> data = new HashMap<>();
		data.put("sortType", sortType);
		data.put("checkedCategory", checkedCategory);
			
		//log.info("데이터 :" + data); 
			
		Map<String, Object> result = new HashMap<>();
			result.put("categoryProduct", service.bestProduct(data));
	 
		//log.info("결과 :" + result);
			 
		return result;
	}
	
	@RequestMapping("/collections/benefits")
	public void GetBenefits(Model model) throws Exception {
		
		model.addAttribute("allCategory", service.allCategory());
	}
	
	//searchProduct
	@RequestMapping("/collections/searchProduct")
	public void GetSearchProductList(Model model,
					 @RequestParam(name="keyword") String keyword,
					 @RequestParam(name="sortType", required=false) String sortType,
					 @RequestParam(value="checkedCategory", required=false) List<String> checkedBrand
													) throws Exception{
		
		log.info("keyword확인: " + keyword);
		
		Map<String, Object> data = new HashMap<>();
		data.put("keyword", keyword);
		data.put("sortType", sortType);
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("categoryFilterSearch", service.categoryFilterSearch(data));
		model.addAttribute("countSearchProduct", service.countSearchProduct(data));
		model.addAttribute("searchProduct", service.searchProduct(data));
		
		log.info("model 확인" + model);	
		
	}
	
	@ResponseBody
	@RequestMapping("/collections/searchProductfilter")
	public Map<String, Object> GetSearchProductfilter(Model model,
				@RequestParam(name="keyword") String keyword,
				@RequestParam(name="sortType", required=false) String sortType,
				@RequestParam(value="checkedCategory[]") List<String> checkedCategory
													) throws Exception{

		//log.info("배열 확인 : " + checkedCategory);
		
		Map<String, Object> data = new HashMap<>();
		data.put("keyword", keyword);
		data.put("sortType", sortType);
		data.put("checkedCategory", checkedCategory);
		
		//log.info("데이터 :" + data); 
		
		 Map<String, Object> result = new HashMap<>();
		 result.put("categoryProduct", service.searchProduct(data));
 
		//log.info("결과 :" + result);
		 
		 return result;
	}
	
	@RequestMapping("/collections/searchNotProduct")
	public void GetSearchNotProduct(Model model, 
					@RequestParam(name="keyword") String keyword) {
	
		model.addAttribute("keyword", keyword);
	}
	
	//상세페이지 내용 보기
	@GetMapping("/product/productdetail")
	public void GetProductdetail(Model model,HttpSession session, @RequestParam("pcode") int pcode,
			 					@RequestParam(name="page") int pageNum, 
			 					@RequestParam(name="searchType", defaultValue="mtitle", required=false) String searchType, 
			 					@RequestParam(name="keyword", defaultValue="", required=false) String keyword) throws Exception{
		ProductVO product = service.productdetail(pcode);
		String userid = (String)session.getAttribute("userid");
		model.addAttribute("productdetail", product);
		
		List<ProductVO> popular = service.popular(pcode);
		model.addAttribute("popular", popular);
		
		
		int listCount = 5;
		int postNum = 5; //한 페이지에 보여질 게시물 목록 갯수
		int startPoint = (pageNum -1)*postNum + 1; 
		int endPoint = postNum*pageNum;
		
		Map<String,Object> data = new HashMap<>();
		data.put("startPoint", startPoint);
		data.put("endPoint", endPoint);
		data.put("searchType", searchType);
		data.put("keyword", keyword);
		data.put("pcode", pcode);
	
		Page page = new Page();
		int totalCount = service.totalCount(data);
		
		System.out.println(service.list(data));
				
		model.addAttribute("list", service.list(data));
		model.addAttribute("page", pageNum);
		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageListView", page.getPageList(pageNum, postNum, listCount, totalCount, searchType, keyword, pcode));
	}
}
