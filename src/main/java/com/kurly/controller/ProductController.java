package com.kurly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurly.dto.BrandVO;
import com.kurly.dto.CategoryVO;
import com.kurly.dto.ProductVO;
import com.kurly.service.ProductService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping("/product/productdetail")
	public void GetDetail(Model model, HttpSession session,
			@RequestParam ("pcode")int pcode) throws Exception {
		
		ProductVO product = service.getProductDetail(pcode);
		String userid = (String)session.getAttribute("userid");
		model.addAttribute("productdetail", product);
	}
	
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
		data.put("checkedBrand", checkedBrand);
		
		
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
}
