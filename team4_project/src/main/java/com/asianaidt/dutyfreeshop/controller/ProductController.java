package com.asianaidt.dutyfreeshop.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.asianaidt.dutyfreeshop.dto.BestProductDTO;
import com.asianaidt.dutyfreeshop.dto.PagingDTO;
import com.asianaidt.dutyfreeshop.dto.ProductBasketDTO;
import com.asianaidt.dutyfreeshop.dto.ProductListDTO;
import com.asianaidt.dutyfreeshop.dto.ReviewRegisterDTO;
import com.asianaidt.dutyfreeshop.service.ProductService;
import com.asianaidt.dutyfreeshop.service.ReviewService;

@RequestMapping("/product")
@Controller
public class ProductController {

	@Autowired
	ProductService service;
	
	@Autowired
	ReviewService reviewService;

	@GetMapping("/productList")
	public ModelAndView productList(@RequestParam(required = false, defaultValue = "1") int categoryId, Model m, @RequestParam(required = false, defaultValue = "1") int currentPage, HttpSession session) throws Exception {
		PagingDTO pagingDto = service.getProductList(categoryId,currentPage);
		
		if (session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME) != null
				&& session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME).toString().equals("en")) {

			for (int i = 0; i < pagingDto.getProductListDtoList().size(); i++) {
				ProductListDTO dto = pagingDto.getProductListDtoList().get(i);
				dto.setBrandNameKor(dto.getBrandNameEng());
				dto.setNameKor(dto.getNameEng());
				dto.setCategoryIdKor(dto.getCategoryIdEng());
				pagingDto.getProductListDtoList().set(i, dto);
			}
		}
		
		ModelAndView mav = new  ModelAndView();
		mav.addObject("pageDTO", pagingDto);
		mav.setViewName("productList");
		
		return mav;
	}
	@GetMapping("/productOne")
	public String productOne(@RequestParam int productId, Model m) throws Exception {
		ProductListDTO dto = service.getProductOne(productId);
		m.addAttribute("cartSuccessMessage","n");
		m.addAttribute("productOne", dto);
		
		List<ReviewRegisterDTO> reviewRegisterDTO = reviewService.getReview(productId);
		m.addAttribute("review", reviewRegisterDTO);
		
		return "productOne";
	}
	
	@ResponseBody
	@PostMapping("/productBasket")
	public String productBasket(ProductBasketDTO productBasketDto, Model m) throws Exception{
		int n = service.getBasket(productBasketDto);
		if(n==0) {
			service.productToBasket(productBasketDto);
			return "y";
		}else {
			return "n";
		}		
	}
	
	@ResponseBody
	@GetMapping("/Basket")
	public String Basket(ProductBasketDTO productBasketDto, Model m) throws Exception{
		System.out.println("basket");
		service.productToBasket(productBasketDto);
		return "success";
	}
}
