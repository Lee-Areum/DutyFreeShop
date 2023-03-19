package com.asianaidt.dutyfreeshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asianaidt.dutyfreeshop.dto.AirlineDTO;
import com.asianaidt.dutyfreeshop.dto.MemberDTO;
import com.asianaidt.dutyfreeshop.dto.RegionDTO;
import com.asianaidt.dutyfreeshop.mapper.OrderMapper;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderMapper mapper;

	@Override
	public List<RegionDTO> getRegion() throws Exception {
		return mapper.getRegion();
	}

	@Override
	public List<AirlineDTO> searchAirline(AirlineDTO dto) throws Exception {
		return mapper.searchAirline(dto);
	}
	
	@Override
	public MemberDTO getUserData(int memberIdx) throws Exception {
		return mapper.getUserData(memberIdx);
	}

	@Override
	public float getExchange(String exchangeUnit) throws Exception {
		return mapper.getExchange(exchangeUnit);
	}

	@Override
	public List<Map<String, Object>> getBasketByUser(Map<String, Object> map) throws Exception {
		return mapper.getBasketByUser(map);
	}

	@Override
	public int insertPayment(Map<String, Object> map) throws Exception {
		return mapper.insertPayment(map);
	}

	@Override
	public int insertOrder(Map<String, Object> map) throws Exception {
		return mapper.insertOrder(map);
	}

	@Override
	public int deleteBasket(int basketId) throws Exception {
		return mapper.deleteBasket(basketId);
	}
}
