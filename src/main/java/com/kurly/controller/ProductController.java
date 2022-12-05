package com.kurly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
