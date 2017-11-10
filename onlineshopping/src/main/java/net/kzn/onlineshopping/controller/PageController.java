package net.kzn.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Category;
import net.kzn.shoppingbackend.dto.Product;

@Controller
public class PageController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

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

	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showsingleProduct(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("page");
		System.out.println("+++++++++++++++++++++++++++++inside the show id product+++++++++++++++++++++++++++++");

		Product product = null;
		product = productDAO.get(id);
		// product = productDAO.get(id);
		System.out.println(product);
		System.out.println("+++++++++++++++++++++++++++++inside the show id product+++++++++++++++++++++++++++++");

		// updateing the view count
		product.setViews(product.getViews() + 1);
		productDAO.update(product);
		// ---------------------------------------------
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userclicksingleProduct", true);
		return mv;
	}

	/*
	 * Login/
	 * 
	 * Login page controller
	 *
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout
			) {
		ModelAndView mv = new ModelAndView("login");
		if (error != null) {
			mv.addObject("message", "Invalid username and password");
		}
		
		if (logout != null) {
			mv.addObject("logout", "you are logged out successfully");
		}
		mv.addObject("title", "Login");
		return mv;

	}

	// Access Desnied

	// contact mapping
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 -Access Denied");
		mv.addObject("errorTitle", "Aha! Caught You.");
		mv.addObject("errorDescription", "You are not authorized to view this page !");
		return mv;
	}

	// Logout
	@RequestMapping(value = "/perform-logout")

	public String logout(HttpServletRequest request, HttpServletResponse response) {

		// First we are going to fetch the authentication

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);

		}

		return "redirect:/login?logout";
	}

}
