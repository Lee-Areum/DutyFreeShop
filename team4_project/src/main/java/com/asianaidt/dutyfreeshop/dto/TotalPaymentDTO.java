package com.asianaidt.dutyfreeshop.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalPaymentDTO {

	BigDecimal priceUsd;
	BigDecimal priceKrw;

}
