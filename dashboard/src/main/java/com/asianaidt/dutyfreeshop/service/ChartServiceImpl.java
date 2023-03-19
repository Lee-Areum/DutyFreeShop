package com.asianaidt.dutyfreeshop.service;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.asianaidt.dutyfreeshop.dto.ChartDTO;
import com.asianaidt.dutyfreeshop.mapper.ChartMapper;


@Service
public class ChartServiceImpl implements ChartService {

	@Autowired
	ChartMapper mapper;

	@Override
	public List<ChartDTO> getAmountByMonth() throws Exception {
		return mapper.getAmountByMonth();
	}

	@Override
	public List<ChartDTO> getAmountByCategory() throws Exception {
		
		return mapper.getAmountByCategory();
	}

}
