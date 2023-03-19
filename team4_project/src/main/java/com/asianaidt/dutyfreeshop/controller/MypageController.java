package com.asianaidt.dutyfreeshop.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asianaidt.dutyfreeshop.dto.MyPageMemberDTO;
import com.asianaidt.dutyfreeshop.dto.PaymentDTO;
import com.asianaidt.dutyfreeshop.dto.PaymentDetailDTO;
import com.asianaidt.dutyfreeshop.dto.PaymentListDTO;
import com.asianaidt.dutyfreeshop.dto.TotalPaymentDTO;
import com.asianaidt.dutyfreeshop.service.PaymentService;

@Controller
public class MypageController {
	
	@Autowired
	PaymentService service;
	
	@GetMapping("/mypage")
	public String myPage(Model m) throws Exception{
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		System.out.println(userDetails.toString());
		String username = userDetails.getUsername();
		int memberId = Integer.parseInt(username);
		ArrayList<PaymentDTO> list = service.getPayment(memberId);
		TotalPaymentDTO totalPayment = service.getTotalPayment(memberId);
		ArrayList<PaymentListDTO> paymentList = service.getPaymentList(memberId);
		Set<PaymentListDTO> setPaymentList = new HashSet<PaymentListDTO>(paymentList);
		ArrayList<PaymentListDTO> paymentList2 = new ArrayList(setPaymentList);
		
		MyPageMemberDTO myPageMember = service.getMyPageMember(memberId);
		
		m.addAttribute("list", list);
		m.addAttribute("totalPayment", totalPayment);
		m.addAttribute("paymentList", paymentList2);
		m.addAttribute("myPageMember", myPageMember);
		
		return "mypage";
	}
	
	@GetMapping("/mypageDetail")
	public String myPageDetail(@RequestParam int paymentId, Model m) throws Exception{
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		System.out.println(userDetails.toString());
		String username = userDetails.getUsername();
		int memberId = Integer.parseInt(username);
		ArrayList<PaymentDetailDTO> list = service.getPaymentDetail(memberId, paymentId);
		m.addAttribute("paymentDetail", list);
		
		return "mypageDetail";
	}

}
