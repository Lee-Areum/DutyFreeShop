package com.asianaidt.dutyfreeshop.service;

import java.util.ArrayList;

import com.asianaidt.dutyfreeshop.dto.BestProductDTO;

public interface BestProductService {
	
	public ArrayList<BestProductDTO> getBestProductList() throws Exception;

}
