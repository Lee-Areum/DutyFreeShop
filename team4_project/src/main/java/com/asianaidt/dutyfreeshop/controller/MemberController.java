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

import com.asianaidt.dutyfreeshop.dto.IdCheckDTO;
import com.asianaidt.dutyfreeshop.dto.MemberDTO;
import com.asianaidt.dutyfreeshop.service.MemberService;

@RequestMapping("/members")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/signup")
    public String signupForm(Model model) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
        model.addAttribute("memberFormDto", new MemberDTO());
        return "members/signupForm";
    }

    @PostMapping("/signup")
    public String signup(MemberDTO member, BindingResult bindingResult, Model model) throws Exception {
    	
        if (bindingResult.hasErrors()) {
            return "members/signupForm";
        }

        try {
            memberService.signup(member);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "members/signupForm";
        }

        return "redirect:/";
    }
    
    @GetMapping(value = "/idcheck")
    @ResponseBody
    public ResponseEntity<IdCheckDTO> idCheck(@RequestParam String id) throws Exception {
    	int cnt = memberService.getIdCheck(id);
    	IdCheckDTO idCheckDTO = new IdCheckDTO();
    	if (cnt != 0) {
    		idCheckDTO.setExist(true);
    	}
    	return new ResponseEntity<IdCheckDTO>(idCheckDTO, HttpStatus.OK);
    }

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
