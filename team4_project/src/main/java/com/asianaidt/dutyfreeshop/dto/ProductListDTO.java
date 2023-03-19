package com.asianaidt.dutyfreeshop.dto;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("ProductListDTO")
public class ProductListDTO {
	int productId;	
	String nameKor;
	String nameEng;
	String imageUrl;
	String contentKor;
	String contentEng;
	int originalPrice;
	BigDecimal saleRate;
	BigDecimal discountPrice;
	BigDecimal discountWon;
	int totalCnt;
	int categoryId;
	String categoryIdKor;
	String categoryIdEng;
	String brandNameKor;
	String brandNameEng;
}
