package com.asianaidt.dutyfreeshop.dto;

import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("BasketDTO")
public class BasketDTO {
	int basketId;
	int memberId;
	int productId;
	int amount;
	String nameKor;
	int originalPrice;
	String imageUrl;
	BigDecimal exchangeRate;
	BigDecimal saleRate;
}
