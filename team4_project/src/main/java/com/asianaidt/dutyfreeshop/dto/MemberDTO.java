package com.asianaidt.dutyfreeshop.dto;

import org.apache.ibatis.type.Alias;

import com.asianaidt.dutyfreeshop.constant.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Alias("MemberDTO")
public class MemberDTO {
	
	int memberId;
	String id;
	String password;
	String name;
	String englishFirst;
	String englishLast;
	String email;
	String phoneNumber;
	String birthDate;
	Role role;
	String createDate;
	String updateDate;
	String deleteDate;
}
