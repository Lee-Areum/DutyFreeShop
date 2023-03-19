package com.asianaidt.dutyfreeshop.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.dutyfreeshop.dto.ProductListDTO;

@Mapper
public interface SearchProductMapper {

	ArrayList<ProductListDTO> getSearchData(String searchText) throws Exception;
	
}
