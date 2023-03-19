package com.asianaidt.dutyfreeshop.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

	int paymentId;
	int memberId;
	String orderFirstName;
	String orderLastName;
	String orderName;
	String phoneNumber;
	String passportNumber;
	String birthDate;
	String email;
	String nation;
	String paymentDate;
	char paymentMethod;
	String uniquePaymentNumber;
	String status;
	BigDecimal priceUsd;
	BigDecimal priceKrw;
}
