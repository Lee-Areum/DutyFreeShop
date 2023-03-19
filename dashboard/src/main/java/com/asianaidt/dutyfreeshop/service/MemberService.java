package com.asianaidt.dutyfreeshop.service;

import com.asianaidt.dutyfreeshop.dto.MemberDTO;


public interface MemberService {

	public void signup(MemberDTO member) throws Exception;
	public int getIdCheck(String id) throws Exception;

}
