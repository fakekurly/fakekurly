package com.kurly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kurly.dto.CartVO;
import com.kurly.dto.MemberVO;
import com.kurly.dto.OrderVO;
import com.kurly.service.OrderService;

@Controller
public class OrderController {

	
	@Autowired
	OrderService service;

	//장바구니에서 구매-> 주문서 출력
	//체크한 상품 코드들만 받아 
//	@ResponseBody
//	@PostMapping("/order/goorder")
//	public void postGoOrder(HttpServletRequest request) {
////		List<String> str_code  = (List<String>) request.getParameterValues("codes");
////		
////		int[] code = new int[str_code.size()];
////		for(int i=0;i<str_code.size();i++){
////				code[i] = Integer.parseInt(str_code.get(i));
////		}
////		
////		for(int i=0;i<code.length;i++){
////			service.deleteCheck(code[i], customer);
////		}
	
	@RequestMapping("/order/order")
	public void goOrderSheet(HttpServletRequest request,Model model) {

		HttpSession session = request.getSession();  
		String[] str_code  = request.getParameterValues("buy");
		String customer = (String)session.getAttribute("userid"); 
		
		int[] code = new int[str_code.length];
		for(int i=0;i<str_code.length;i++){
			code[i] = Integer.parseInt(str_code[i]);
		}
		
		//주문할 상품 출력
//		List<OrderVO> list = new ArrayList<>();
//		OrderVO orderVO;
//		for(int i=0;i<code.length;i++) {
//			
//			list=service.goOrderSheet(code[i], customer);
//		}
//		
//		for(OrderVO order : list) {
//			System.out.print(order);
//		}
		
		List<OrderVO> list = new ArrayList<>();


		
		for(int i=0;i<code.length;i++) {
			OrderVO vo = new OrderVO();
			vo=service.goOrderSheet(code[i], customer);
			list.add(vo);
		}
		
		
		model.addAttribute("list", list);
		
		List<MemberVO> userInfo = new ArrayList<>();
		userInfo = service.customerInfo(customer);
		
		model.addAttribute("userInfo", userInfo);
		
	}
	
	@ResponseBody
	@PostMapping("/order/goorder")
	public void goOrder(@RequestBody Map<String, Object> orderData,HttpServletRequest request) {
		System.out.println(orderData.get("code"));
		
		HttpSession session = request.getSession();
		List<String> str_code  = (List<String>) orderData.get("code");
		String customer = (String)session.getAttribute("userid"); 
		
		int[] code = new int[str_code.size()];
		for(int i=0;i<str_code.size();i++){
				code[i] = Integer.parseInt(str_code.get(i));
		}
		
		for(int i=0;i<code.length;i++){
			service.goOrder(code[i], customer);
		}
		
		//주문 상품 tbl_order 삽입
		service.insertOrder();
		
		//주문 후 tbl_product 재고 수정
		service.updateStock(customer);
		
		//주문한 상품 수 tbl_product order_quantity 추가
		service.updateOrderQuan();
		
		
		
		//장바구니 주문 -> 주문 처리 후 장바구니에서 삭제하기
		service.delOrderCart();
		
	}
	
	
	@GetMapping("/order/orderlist")
	public void GetOrderList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		String customer = (String)session.getAttribute("userid");
		
		
		model.addAttribute("cnt", service.checkOrder(customer)); //주문내역 있는 지 없는 지
		
		List<OrderVO> list = new ArrayList<>();
		list = service.getOrderList(customer);

		model.addAttribute("list", list);
		
	}
}

