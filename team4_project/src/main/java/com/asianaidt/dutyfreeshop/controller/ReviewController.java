package com.asianaidt.dutyfreeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asianaidt.dutyfreeshop.dto.ReviewCheckDTO;
import com.asianaidt.dutyfreeshop.dto.ReviewCheckRequestDTO;
import com.asianaidt.dutyfreeshop.dto.ReviewRegisterDTO;
import com.asianaidt.dutyfreeshop.service.ReviewService;

@RequestMapping("/review")
@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping(value = "/reviewcheck")
    @ResponseBody
    public ResponseEntity<ReviewCheckDTO> reviewCheck(@RequestParam int productId) throws Exception {
		
		ReviewCheckRequestDTO reviewCheckRequestDTO = new ReviewCheckRequestDTO();
		reviewCheckRequestDTO.setProductId(productId);
		
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		String username = userDetails.getUsername();
		int memberIdx = Integer.parseInt(username);
		reviewCheckRequestDTO.setMemberId(memberIdx);
		
		ReviewCheckDTO reviewCheckDTO = reviewService.reviewCheck(reviewCheckRequestDTO);
		
    	return new ResponseEntity<ReviewCheckDTO>(reviewCheckDTO, HttpStatus.OK);
    }
	
	@PostMapping(value = "/register")
	@ResponseBody
    public String register(ReviewRegisterDTO reviewRegisterDTO) throws Exception {		
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		String username = userDetails.getUsername();
		int memberIdx = Integer.parseInt(username);
		reviewRegisterDTO.setMemberId(memberIdx);
		
		reviewService.insertReview(reviewRegisterDTO);
		return "success";
    }

}
