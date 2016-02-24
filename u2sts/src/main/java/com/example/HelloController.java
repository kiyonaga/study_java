package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/Hello")
public class HelloController
{
	@Autowired
	private HogeService svc;

	@RequestMapping(method=RequestMethod.GET)
	public String hello(Model model)
	{
		svc.insertRandam();

		model.addAttribute("list", svc.select());
		return "hello";
	}
}
