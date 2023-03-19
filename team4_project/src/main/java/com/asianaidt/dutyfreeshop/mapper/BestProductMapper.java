package com.asianaidt.dutyfreeshop.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.dutyfreeshop.dto.BestProductDTO;

@Mapper
public interface BestProductMapper {

	public ArrayList<BestProductDTO> getBestProductList() throws Exception;
}
