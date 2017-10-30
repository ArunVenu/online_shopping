package net.kzn.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

@Controller
public class PageController {

	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value = "/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		// passing the value
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userclickHome", true);
		return mv;
	}

	@RequestMapping(value = "/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userclickHome", true);
		return mv;
	}

	// about mapping
	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userclickAbout", true);
		return mv;
	}

	// contact mapping
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userclickContact", true);
		return mv;
	}

	// methods to load all the list of products
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");

		// passig the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userclickAllProducts", true);
		return mv;

	}

	// methods to load single products
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");

		// categoryDAO to fetch single category
		Category category = null;
		category = categoryDAO.get(id);

		mv.addObject("title", category.getName());

		// passig the list of categories
		mv.addObject("categories", categoryDAO.list());
		
		// passing the single category
		mv.addObject("category", category);
		mv.addObject("userclickCategoryProducts", true);
		return mv;

	}
	/*
	 * // RequestParam
	 * 
	 * @RequestMapping(value = "/test") public ModelAndView
	 * test(@RequestParam("greeting") String greeting) { ModelAndView t = new
	 * ModelAndView("page"); t.addObject("greeting", greeting); return t; }
	 * 
	 * // RequestParam
	 * 
	 * @RequestMapping(value = "/test1") public ModelAndView
	 * test1(@RequestParam(value = "greeting", required = false) String greeting) {
	 * if (greeting == null) { greeting = "hello greeting"; } ModelAndView t = new
	 * ModelAndView("page"); t.addObject("greeting", greeting); return t; }
	 * 
	 * // pathvariable
	 * 
	 * @RequestMapping("/pathtest/{pathvar}") public ModelAndView
	 * path(@PathVariable("pathvar") String path) { ModelAndView mvpath = new
	 * ModelAndView("page"); mvpath.addObject("greeting", path); return mvpath;
	 * 
	 * }
	 */
}
