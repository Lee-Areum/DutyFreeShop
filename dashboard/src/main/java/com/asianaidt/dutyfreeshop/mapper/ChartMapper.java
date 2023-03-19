package com.asianaidt.dutyfreeshop.mapper;

import java.math.BigDecimal;
import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.dutyfreeshop.dto.ChartDTO;




@Mapper
public interface ChartMapper {
	public List<ChartDTO> getAmountByMonth() throws Exception;
	public List<ChartDTO> getAmountByCategory() throws Exception;
}
