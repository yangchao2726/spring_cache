package org.yc.spring_cache.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) {
		return new ModelAndView("home");
	}
	
	@RequestMapping(value="/index")
	public String index() {
		return "home";
	}
}
