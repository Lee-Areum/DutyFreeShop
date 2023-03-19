package com.asianaidt.dutyfreeshop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.dutyfreeshop.dto.AirlineDTO;
import com.asianaidt.dutyfreeshop.dto.MemberDTO;
import com.asianaidt.dutyfreeshop.dto.RegionDTO;


@Mapper
public interface OrderMapper {

	public List<RegionDTO> getRegion()throws Exception;
	
	public List<AirlineDTO> searchAirline(AirlineDTO dto)throws Exception;
	
	public List<Map<String,Object>> getBasketByUser(Map<String,Object> map)throws Exception;
	
	public MemberDTO getUserData(int memberIdx)throws Exception;

	public float getExchange(String exchangeUnit) throws Exception;
	
	public int insertPayment(Map<String,Object> map) throws Exception;
	
	public int insertOrder(Map<String,Object> map) throws Exception;

	public int deleteBasket(int basketId)throws Exception;
}
