package com.asianaidt.dutyfreeshop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.asianaidt.dutyfreeshop.dto.BestProductDTO;
import com.asianaidt.dutyfreeshop.dto.ExchangeRateDTO;
import com.asianaidt.dutyfreeshop.dto.ProductListDTO;
import com.asianaidt.dutyfreeshop.service.BestProductService;
import com.asianaidt.dutyfreeshop.service.ExchangeRateService;
import com.asianaidt.dutyfreeshop.service.SearchProductService;

@Controller
public class MainController {
	
	@Autowired
	BestProductService service;
	
	@Autowired
	ExchangeRateService exchangeService;
	
	@Autowired
	SearchProductService searchService;
	
	
	@GetMapping("/")
	public String index(Model m, HttpSession session) throws Exception{
		ArrayList<BestProductDTO> bestProductList = service.getBestProductList();
		ExchangeRateDTO exchangeRate = exchangeService.getExchangeRate();
		
		if (session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME) != null
				&& session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME).toString().equals("en")) {

			for (int i = 0; i < bestProductList.size(); i++) {
				BestProductDTO dto = bestProductList.get(i);
				dto.setBrandNameKor(dto.getBrandNameEng());
				dto.setNameKor(dto.getNameEng());
				bestProductList.set(i, dto);
			}
		}
		
		m.addAttribute("bestProductList", bestProductList);
		m.addAttribute("exchangeRate", exchangeRate);
		
		return "home_page";
	}
	
	
	@GetMapping("/searchproduct")
	public String searchProduct(@RequestParam String searchText, Model m) throws Exception{
		System.out.println(searchText);
		ArrayList<ProductListDTO> searchData = searchService.getSearchData(searchText);
		ExchangeRateDTO exchangeRate = exchangeService.getExchangeRate();
		
		m.addAttribute("searchData", searchData);
		m.addAttribute("exchangeRate", exchangeRate);
		
		return "searchProduct";
	}
	
	
	
}
