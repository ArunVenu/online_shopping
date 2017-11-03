package net.kzn.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
		product =productDAO.get(id);
		//product = productDAO.get(id);
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

}
