package com.asianaidt.dutyfreeshop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asianaidt.dutyfreeshop.dto.BestProductDTO;
import com.asianaidt.dutyfreeshop.mapper.BestProductMapper;

@Service
public class BestProductServiceImpl implements BestProductService{

	@Autowired
	BestProductMapper mapper;
	

	@Override
	public ArrayList<BestProductDTO> getBestProductList() throws Exception {		
		return mapper.getBestProductList();
	}

}
