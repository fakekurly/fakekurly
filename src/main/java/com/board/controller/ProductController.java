package com.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.dto.ProductVO;
import com.board.service.ProductService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping("/product/detail")
	public void GetDetail(Model model, HttpSession session,
			@RequestParam ("pcode")int pcode) throws Exception {
		
		ProductVO product = service.getProductDetail(pcode);
		
		model.addAttribute("detail", product);
	}
}
