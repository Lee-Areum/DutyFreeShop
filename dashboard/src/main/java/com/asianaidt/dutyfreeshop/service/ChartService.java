package com.asianaidt.dutyfreeshop.service;

import java.math.BigDecimal;
import java.util.List;

import com.asianaidt.dutyfreeshop.dto.ChartDTO;




public interface ChartService {
	public List<ChartDTO> getAmountByMonth() throws Exception;
	public List<ChartDTO> getAmountByCategory() throws Exception;
	
}
