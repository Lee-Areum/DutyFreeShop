package com.asianaidt.dutyfreeshop.dto;



import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("ChartDTO")
public class ChartDTO {
	int orderId;
	int memberId;
	int paymentId;
	int productId;
	int amount;
	int priceUsd;
	int priceKrw;
	String paymentDate;
	
	int month;
	int sum;
	String categoryIdKor;
	
	
}
