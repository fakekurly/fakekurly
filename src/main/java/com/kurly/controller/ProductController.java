package com.kurly.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//상세페이지 내용 보기
		@GetMapping("/product/productdetail")
		public void GetProductdetail(Model model,HttpSession session, @RequestParam("pcode") int pcode) throws Exception{
			
			ProductVO product = service.productdetail(pcode);
			String userid = (String)session.getAttribute("userid");
			model.addAttribute("productdetail", product);
			
		}
		
	
	
	

}
