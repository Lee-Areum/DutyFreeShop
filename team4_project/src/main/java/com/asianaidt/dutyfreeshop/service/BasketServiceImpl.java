package com.asianaidt.dutyfreeshop.service;

import java.math.BigDecimal;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asianaidt.dutyfreeshop.dto.BasketDTO;

import com.asianaidt.dutyfreeshop.mapper.BasketMapper;


@Service
public class BasketServiceImpl implements BasketService {

	@Autowired
	BasketMapper mapper;

	@Override
	public List<BasketDTO> getBasket(int num) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getBasket(num);
	}

	@Transactional
	@Override
	public int deleteBasket(int num) throws Exception {
		
		return mapper.deleteBasket(num);
	}
	
	@Transactional
	@Override
	public int updateItem(BasketDTO dto) throws Exception {
		return mapper.updateItem(dto);
	}

	@Transactional
	@Override
	public int basketMultiDelete(List<Integer> list) throws Exception {
		
		return mapper.basketMultiDelete(list);
	}

	@Override
	public BigDecimal getExchangeRate(String money) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		return mapper.getExchangeRate(money);
	}
	

}
