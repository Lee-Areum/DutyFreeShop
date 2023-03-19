package com.asianaidt.dutyfreeshop.controller;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Controller
public class LocaleController {
	
	@Bean 
	public SessionLocaleResolver localeResolver() {
	    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
	    sessionLocaleResolver.setDefaultLocale(new Locale("ko"));
	    return sessionLocaleResolver;
	}
	
	@RequestMapping("/changeLocale")
	public String changeLocale(Locale xxx, @RequestParam(defaultValue = "ko") String lang, HttpSession session) {
		session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(lang));

		return "redirect:/";
	}
}
