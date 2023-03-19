package com.asianaidt.dutyfreeshop.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.dutyfreeshop.dto.ExchangeRateDTO;

@Mapper
public interface ExchangeRateMapper {
	
	public ExchangeRateDTO getExchangeRate() throws Exception;

}
