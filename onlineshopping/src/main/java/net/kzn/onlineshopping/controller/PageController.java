package net.kzn.onlineshopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	@RequestMapping(value = "/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userclickHome", true);
	return mv;
	}
	
	@RequestMapping(value = "/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userclickHome", true);
	return mv;
	}
	//about mapping
	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userclickAbout", true);
	return mv;
	}
	//contact mapping
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userclickContact", true);
	return mv;
	}
	//listproducts mappin
	@RequestMapping(value = "/listproducts")
	public ModelAndView listproducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "ListProducts");
		mv.addObject("userclickListproducts", true);
	return mv;
	
	}
	
	
	
	
/*
	// RequestParam
	@RequestMapping(value = "/test")
	public ModelAndView test(@RequestParam("greeting") String greeting) {
		ModelAndView t = new ModelAndView("page");
		t.addObject("greeting", greeting);
		return t;
	}

	// RequestParam
	@RequestMapping(value = "/test1")
	public ModelAndView test1(@RequestParam(value = "greeting", required = false) String greeting) {
		if (greeting == null) {
			greeting = "hello greeting";
		}
		ModelAndView t = new ModelAndView("page");
		t.addObject("greeting", greeting);
		return t;
	}

	// pathvariable
	@RequestMapping("/pathtest/{pathvar}")
	public ModelAndView path(@PathVariable("pathvar") String path) {
		ModelAndView mvpath = new ModelAndView("page");
		mvpath.addObject("greeting", path);
		return mvpath;

	}*/
}
