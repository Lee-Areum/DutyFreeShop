package com.asianaidt.dutyfreeshop.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentListDTO {
	
	int paymentId;
	int memberId;
	String uniquePaymentNumber;
	String orderFirstName;
	String orderLastName;
	String orderName;
	String imageUrl;
	String nameKor;
	String nameEng;
	int extraCnt;
	String paymentDate;
	String status;
	BigDecimal sumPriceUsd;
	BigDecimal sumPriceKrw;
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentListDTO other = (PaymentListDTO) obj;
		if (paymentId != other.paymentId)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + paymentId;
		return result;
	}
	
	
	
}
