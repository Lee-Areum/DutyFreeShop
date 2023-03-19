package com.asianaidt.dutyfreeshop.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.asianaidt.dutyfreeshop.dto.MemberDTO;


@Mapper
public interface MemberMapper {

	public void signup(MemberDTO member) throws Exception;
	public MemberDTO getMember(String id) throws Exception;
	public int getIdCheck(String id) throws Exception;

}
