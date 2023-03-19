package com.asianaidt.dutyfreeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageMemberDTO {
	
	int memberId;
	String name;
	String englishFirst;
	String englishLast;

}
