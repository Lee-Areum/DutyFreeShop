package com.asianaidt.dutyfreeshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.asianaidt.dutyfreeshop.dto.IdCheckDTO;
import com.asianaidt.dutyfreeshop.dto.ReviewCheckDTO;
import com.asianaidt.dutyfreeshop.dto.ReviewCheckRequestDTO;
import com.asianaidt.dutyfreeshop.dto.ReviewRegisterDTO;
import com.asianaidt.dutyfreeshop.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private ReviewMapper reviewMapper;

	@Override
	public ReviewCheckDTO reviewCheck(ReviewCheckRequestDTO reviewCheckRequestDTO) throws Exception {
		int result1 = reviewMapper.getCheckOrder(reviewCheckRequestDTO);
		int result2 = reviewMapper.getCheckReview(reviewCheckRequestDTO);
		
		boolean isPossible = true;
	
		if (result1 == 0 || result2 != 0) {
			isPossible = false;
		}
		
		ReviewCheckDTO reviewCheckDTO = new ReviewCheckDTO();
		reviewCheckDTO.setPossible(isPossible);
		
		return reviewCheckDTO;
	}
	
	@Override
	public int insertReview(ReviewRegisterDTO reviewRegisterDTO) throws Exception {
		return reviewMapper.insertReview(reviewRegisterDTO);
	}

	@Override
	public List<ReviewRegisterDTO> getReview(int productId) throws Exception {
		return reviewMapper.getReview(productId);
	}


}
