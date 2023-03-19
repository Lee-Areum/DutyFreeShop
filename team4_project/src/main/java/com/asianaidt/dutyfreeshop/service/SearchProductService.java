package com.asianaidt.dutyfreeshop.service;

import java.util.ArrayList;

import com.asianaidt.dutyfreeshop.dto.ProductListDTO;

public interface SearchProductService {

	ArrayList<ProductListDTO> getSearchData(String searchText) throws Exception;
	
}
