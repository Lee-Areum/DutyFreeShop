package com.asianaidt.dutyfreeshop.service;

import com.asianaidt.dutyfreeshop.dto.PagingDTO;
import com.asianaidt.dutyfreeshop.dto.ProductBasketDTO;
import com.asianaidt.dutyfreeshop.dto.ProductListDTO;

public interface ProductService {
	public PagingDTO getProductList(int categoryId, int currentPage)throws Exception;
	public ProductListDTO getProductOne(int productId)throws Exception;
	public int productToBasket(ProductBasketDTO productBasketDto) throws Exception;
	public int updateProductBasket(ProductBasketDTO productBasketDto) throws Exception;
	public int getBasket(ProductBasketDTO productBasketDto) throws Exception;
}
