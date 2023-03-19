package com.asianaidt.dutyfreeshop.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.asianaidt.dutyfreeshop.dto.ReviewCheckDTO;
import com.asianaidt.dutyfreeshop.dto.ReviewCheckRequestDTO;
import com.asianaidt.dutyfreeshop.dto.ReviewRegisterDTO;

public interface ReviewService {

	public ReviewCheckDTO reviewCheck(ReviewCheckRequestDTO reviewCheckRequestDTO) throws Exception;
	public int insertReview(ReviewRegisterDTO reviewRegisterDTO) throws Exception;
	public List<ReviewRegisterDTO> getReview(int productId) throws Exception;

}
