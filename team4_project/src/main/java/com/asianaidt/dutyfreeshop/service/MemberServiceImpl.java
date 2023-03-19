package com.asianaidt.dutyfreeshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asianaidt.dutyfreeshop.constant.Role;
import com.asianaidt.dutyfreeshop.dto.MemberDTO;
import com.asianaidt.dutyfreeshop.mapper.MemberMapper;

@Service
@Transactional
public class MemberServiceImpl implements MemberService, UserDetailsService {
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberDTO member = null;
		try {
			member = memberMapper.getMember(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (member == null) {
			throw new UsernameNotFoundException(id);
		}
		
		return User.builder()
                .username(String.valueOf(member.getMemberId()))
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
	}

	@Override
	public void signup(MemberDTO member) throws Exception {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		member.setRole(Role.USER);
		
		memberMapper.signup(member);
	}

	@Override
	public int getIdCheck(String id) throws Exception {
		return memberMapper.getIdCheck(id);
	}

}
