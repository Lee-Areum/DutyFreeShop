package com.asianaidt.dutyfreeshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asianaidt.dutyfreeshop.dto.ExchangeRateDTO;
import com.asianaidt.dutyfreeshop.mapper.ExchangeRateMapper;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{
	
	@Autowired
	ExchangeRateMapper mapper;

	@Override
	public ExchangeRateDTO getExchangeRate() throws Exception {
		return mapper.getExchangeRate();
	}

}
