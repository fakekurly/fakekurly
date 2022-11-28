package com.kurly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurly.dto.CartVO;
import com.kurly.service.CartService;

@Controller
public class CartController {
	
	@Autowired
	CartService service;
	//CartVO cartVO;
	//productdetail 장바구니 버튼 클릭 시 -> 장바구니 DB ajax 
	@ResponseBody
	@PostMapping("/cart/cartproc")
	public void postCart(@RequestBody Map<String, Object> cartData,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String customer = (String) session.getAttribute("userid");
		System.out.println(customer);
		int pcode = Integer.parseInt((String) cartData.get("code"));
		int pcount = Integer.parseInt((String) cartData.get("count"));
		int cnt = service.isInCart(customer,pcode);
		
		
		System.out.println(cnt);
		
		//cnt=0? 카트 insert : update
		if(cnt==0)
			service.insertCart(customer, pcode, pcount);
		else
			service.updateCart(customer, pcode, pcount);
	}
	
	//cartlist 화면
	@GetMapping("/cart/cartlist")
	public void GetCartList(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();  
		String customer = (String) session.getAttribute("userid");
		int cnt = service.checkCart(customer);
		
		//cnt==0이면 장바구니에 담긴 상품이 없습니다. 있으면 목록 출력
		model.addAttribute("checkCart", cnt);
		
		List<CartVO> list = new ArrayList<>();
		list = service.getCartList(customer);

		model.addAttribute("list", list);
	

		
	}

	//장바구니 한 행, 선택, 전체 삭제
	@ResponseBody
	@PostMapping("/cart/deleteproc")
	public void deleteCart(@RequestBody Map<String, Object> cartData,HttpServletRequest request) {
		
		HttpSession session = request.getSession();  

		String delete = (String)cartData.get("deleteWhat");
		String customer = (String) session.getAttribute("userid");
		
		//장바구니 전체 삭제
		if(delete.equals("all"))
			service.deleteAll(customer);
		else if(delete.equals("this")) {
			//상품 코드
			int pcode = Integer.parseInt((String.valueOf(cartData.get("code"))));
			
			service.deleteThis(pcode, customer);
		}
		else if(delete.equals("check")) {
			//체크된 값 받아서 삭제
			System.out.println(cartData.get("codes"));
			
			
			//List<String> str_code = request.getParameterValues("codes");
			
			List<String> str_code  = (List<String>) cartData.get("codes");
			
			int[] code = new int[str_code.size()];
			for(int i=0;i<str_code.size();i++){
					code[i] = Integer.parseInt(str_code.get(i));
			}
			
			for(int i=0;i<code.length;i++){
				service.deleteCheck(code[i], customer);
			}
		}
		//System.out.println(customer);
	}

	//장바구니 수량변경 시 업데이트
	@ResponseBody
	@PostMapping("/cart/updateproc")
	public void updateCart(@RequestBody Map<String, Object> cartData,HttpServletRequest request) {
		HttpSession session = request.getSession();  

		String customer = (String) session.getAttribute("userid");
		
		//업데이트할 상품 코드, 수량 받아
		int pcode = Integer.parseInt((String.valueOf(cartData.get("code"))));
		int pcount = Integer.parseInt((String.valueOf(cartData.get("count"))));
		
		service.modifyCount(pcount, pcode, customer);

	}
}
