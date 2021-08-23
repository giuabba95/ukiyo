package com.giuseppeabbagnale.ukiyo.ukiyo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping("/form")
	public String login() {
		return "/login/form";
	}

}
