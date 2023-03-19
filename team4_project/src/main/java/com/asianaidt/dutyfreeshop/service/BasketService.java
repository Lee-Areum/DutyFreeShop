package com.asianaidt.dutyfreeshop.service;

import java.math.BigDecimal;
import java.util.List;


import com.asianaidt.dutyfreeshop.dto.BasketDTO;


public interface BasketService {
	public List<BasketDTO> getBasket(int num)throws Exception;
	public int deleteBasket(int num) throws Exception;
	public int updateItem(BasketDTO dto) throws Exception;
	public int basketMultiDelete(List<Integer> list) throws Exception;
	public BigDecimal getExchangeRate(String money) throws Exception;
}
