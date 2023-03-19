package com.asianaidt.dutyfreeshop.mapper;

import java.math.BigDecimal;
import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.dutyfreeshop.dto.BasketDTO;



@Mapper
public interface BasketMapper {
	public List<BasketDTO> getBasket(int num)throws Exception;
	public int deleteBasket(int num) throws Exception;
	public int updateItem(BasketDTO dto) throws Exception;
	public int basketMultiDelete(List<Integer> list) throws Exception;
	public BigDecimal getExchangeRate(String money) throws Exception;
}
