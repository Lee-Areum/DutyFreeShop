package com.asianaidt.dutyfreeshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.dutyfreeshop.dto.ReviewCheckRequestDTO;
import com.asianaidt.dutyfreeshop.dto.ReviewRegisterDTO;

@Mapper
public interface ReviewMapper {
	public int getCheckOrder(ReviewCheckRequestDTO reviewCheckRequestDTO) throws Exception;
	public int getCheckReview(ReviewCheckRequestDTO reviewCheckRequestDTO) throws Exception;
	public int insertReview(ReviewRegisterDTO reviewRegisterDTO) throws Exception;
	public List<ReviewRegisterDTO> getReview(int productId) throws Exception;

}
