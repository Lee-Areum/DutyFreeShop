package com.asianaidt.dutyfreeshop.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("ProductBasketDTO")
public class ProductBasketDTO {
	int memberId;
	int productId;
	int amount;
}
