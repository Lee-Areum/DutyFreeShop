package com.asianaidt.dutyfreeshop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.dutyfreeshop.dto.ProductBasketDTO;
import com.asianaidt.dutyfreeshop.dto.ProductDTO;
import com.asianaidt.dutyfreeshop.dto.ProductListDTO;

@Mapper
public interface ProductMapper {
	public List<ProductListDTO> getProductList(int categoryId)throws Exception;
	public ProductListDTO getProductOne(int productId)throws Exception;
	public int productToBasket(ProductBasketDTO productBasketDto) throws Exception;
	public int updateProductBasket(ProductBasketDTO productBasketDto) throws Exception;
	public int getBasket(ProductBasketDTO productBasketDto) throws Exception;
	public List<ProductListDTO> pagingProductList(int categoryId) throws Exception;
	public int pagingProductCount(int categoryId) throws Exception;
}
