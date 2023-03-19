package com.asianaidt.dutyfreeshop.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@RequestMapping("/admin")
@Controller
public class MainController {
	
	@GetMapping("/")
	public String index(Model m) throws Exception{
		return "main";
	}
	
	
}
