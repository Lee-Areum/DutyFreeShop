package com.asianaidt.dutyfreeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asianaidt.dutyfreeshop.dto.ExchangeRateDTO;
import com.asianaidt.dutyfreeshop.dto.IdCheckDTO;
import com.asianaidt.dutyfreeshop.service.ExchangeRateService;

@RequestMapping("/exchangeRate")
@Controller
public class ExchangeRateController {

	@Autowired
	ExchangeRateService exchangeService;
	
	@ResponseBody
	@GetMapping("/today")
	public ResponseEntity<ExchangeRateDTO> getTodayExchangeRate() throws Exception{
		ExchangeRateDTO exchangeRate = exchangeService.getExchangeRate();
		
		return new ResponseEntity<ExchangeRateDTO>(exchangeRate, HttpStatus.OK);
	}
}
