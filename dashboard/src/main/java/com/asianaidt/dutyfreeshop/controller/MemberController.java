package com.asianaidt.dutyfreeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asianaidt.dutyfreeshop.dto.MemberDTO;
import com.asianaidt.dutyfreeshop.service.MemberService;

@RequestMapping("/admin/members")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

    @GetMapping(value = "/login")
    public String loginForm() {
        return "members/loginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "members/loginForm";
    }
}
