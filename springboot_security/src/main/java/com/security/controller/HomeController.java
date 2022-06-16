package com.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


	@RequestMapping("/welcome")
	public String welcome()
	{
		String text= "this is private  ";
		text+="  this  page is not allowed to unthorize user";
		return text;
	}
	
	
	@RequestMapping("/getUsers")
	public String getUser()
	{
		return "{\"name\":\"user\"}";
	}
	
}
