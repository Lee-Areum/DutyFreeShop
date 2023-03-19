package com.asianaidt.dutyfreeshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRegisterDTO {
	int reviewId;
	int memberId;
	int productId;
	double rate;
	String contents;
	String createDate;
	String id;
}