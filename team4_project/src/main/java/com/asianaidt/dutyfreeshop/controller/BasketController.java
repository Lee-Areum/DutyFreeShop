package com.asianaidt.dutyfreeshop.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.asianaidt.dutyfreeshop.dto.BasketDTO;
import com.asianaidt.dutyfreeshop.service.BasketService;

@RequestMapping("/basket")
@Controller
public class BasketController {

	@Autowired
	BasketService service;
	@GetMapping("/basketForm")
	public ModelAndView basketForm() throws Exception {
		// get member id from session

		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		
		String username = userDetails.getUsername();
		int memberIdx = Integer.parseInt(username);
		// call 
		List<BasketDTO> basket_list = service.getBasket(memberIdx);
		
		BigDecimal exchangeRate = service.getExchangeRate("WON");
		
		for(BasketDTO dto:basket_list) {
			dto.setExchangeRate(exchangeRate);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("basketDTOs", basket_list);
		mav.setViewName("basket");
		return mav;
	}
	
	@GetMapping("/delete")
	public String deleteBasket(int num) throws Exception {
		
		int n = service.deleteBasket(num);
		return "redirect:basketForm";
	}
	@GetMapping("/updateItem")
	public String updateItem(BasketDTO dto) throws Exception {
		int n = service.updateItem(dto);
		return "redirect:basketForm";
	}
	
	@GetMapping("/add")
	@ResponseBody
	public int func(@RequestParam int basket_id,@RequestParam int amount) throws Exception {
		BasketDTO dto = new BasketDTO();
		dto.setBasketId(basket_id);
		dto.setAmount(amount+1);
		int n = service.updateItem(dto);
		return amount+1;
	}
	@GetMapping("/subtract")
	@ResponseBody
	public int func2(@RequestParam int basket_id,@RequestParam int amount) throws Exception {
		BasketDTO dto = new BasketDTO();
		dto.setBasketId(basket_id);
		dto.setAmount(amount-1);
		if (amount==1) {
			service.deleteBasket(basket_id);
		}
		else {
			int n = service.updateItem(dto);
		}
		
		return amount-1;
	}
	
	@GetMapping("/multiDelete") //이름이 하나인데 값은 여러개인경우를 받는 법
	public String multiDelete(HttpServletRequest request) throws Exception{

		String[] checks=request.getParameterValues("check");
		
		Integer[] checkint = Stream.of(checks).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
		List<Integer> list = Arrays.asList(checkint);

		int n=service.basketMultiDelete(list);
		return "redirect:basketForm";
	}
	
	
	
	//////////////////////
	@ExceptionHandler({Exception.class})
	public String error() {
		return "error";
	}
}
