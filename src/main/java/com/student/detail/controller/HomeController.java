package com.student.detail.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/index")
	public String home(HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:/login?sessionexpired=true";
		}

		return "index";
	}
}
