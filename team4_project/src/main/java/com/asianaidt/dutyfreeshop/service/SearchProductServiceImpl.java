package com.asianaidt.dutyfreeshop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asianaidt.dutyfreeshop.dto.ProductListDTO;
import com.asianaidt.dutyfreeshop.mapper.SearchProductMapper;

@Service
public class SearchProductServiceImpl implements SearchProductService{
	
	@Autowired
	SearchProductMapper mapper;

	@Override
	public ArrayList<ProductListDTO> getSearchData(String searchText) throws Exception {
		return mapper.getSearchData(searchText);
	}

}
