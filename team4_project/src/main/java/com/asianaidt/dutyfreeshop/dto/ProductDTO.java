package com.asianaidt.dutyfreeshop.dto;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("ProductDTO")
public class ProductDTO {
	int productId;
	int brandId;
	String nameKor;
	String nameEng;	
	String imageUrl;
	int originalPrice;
	BigDecimal saleRate;
	int totalCnt;
	String createdDate;
	String updatedDate;
	String deletedDate;
}
