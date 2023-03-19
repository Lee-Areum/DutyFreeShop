package com.asianaidt.dutyfreeshop.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestProductDTO {

	int productId;
	int categoryId;
	int brandId;
	String nameKor;
	String nameEng;
	String imageUrl;
	int originalPrice;
	BigDecimal saleRate;
	String categoryIdKor;
	String categortIdEng;
	String brandNameKor;
	String brandNameEng;
	
}
