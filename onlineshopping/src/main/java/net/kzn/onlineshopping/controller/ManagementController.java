package net.kzn.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.util.FileUploadUtility;
import net.kzn.onlineshopping.validator.ProductValidator;
import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Category;
import net.kzn.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userclickManageProducts", true);
		mv.addObject("title", "Manage Products");

		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mv.addObject("product", nProduct);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product added Successfully!");
			}
			else if(operation.equals("category")) {
				mv.addObject("message", "Category added Successfully!");
				
			}
		}

		return mv;
	}

	// Edit Form with already presented value

	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)

	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userclickManageProducts", true);
		mv.addObject("title", "Manage Products");
		// get old product by passing the id
		Product nProduct = productDAO.get(id);

		// set the product fetch from the database

		mv.addObject("product", nProduct);

		return mv;
	}

	// Handling the product to save the data into database

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult result,
			Model model, HttpServletRequest request) {

		
		//validation for product form
		if(mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, result);
		}else {
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, result);
			}
		}
		

		// check If there any Exception
		if (result.hasErrors()) {

			model.addAttribute("userclickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission");

			return "page";
		}

		if(mProduct.getId() == 0) {
			//create a new product if id is 0
			productDAO.add(mProduct);
		}
		else
		{
			//update the product when id is not equal to 0
			productDAO.update(mProduct);
		}
		
	
		if (!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());

		}

		return "redirect:/manage/products?operation=product";
	}

	//to handle category submission
	
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
	}
	
	
	
	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String manageProductActivationDeActivation(@PathVariable int id) {
		// is going to fetch the product from the database

		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		// Activateing deactivating based on the value of active field
		product.setActive(!product.isActive());
		// updating the product
		productDAO.update(product);

		return (isActive) ? "You have successfully deactivated the product with id " + product.getId()
				: "You have successfully deactivated the product with id " + product.getId();

	}

	// returning categories for all the request
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	
	
	
		@ModelAttribute("category")
		public Category getCategory() {
			return new Category();
		}
}
