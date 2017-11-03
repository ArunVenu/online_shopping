package net.kzn.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView showManageProducts(@RequestParam (name="operation", required = false)String operation){
		ModelAndView mv = new ModelAndView("page");

		mv.addObject("userclickManageProducts", true);
		mv.addObject("title", "Manage Products");

		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);

		mv.addObject("product", nProduct);
		
		if (operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message", "Product added Successfully!");
			}
		}

		return mv;
	}


	//Handling the product to save the data into database
	
	@RequestMapping(value="/products",method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,BindingResult result,Model model,HttpServletRequest request) {
		
		new ProductValidator().validate(mProduct, result);
		
		//check If there any Exception
		if(result.hasErrors()) {
			
			model.addAttribute("userclickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for Product Submission");

			return "page";
		}
		
		
		productDAO.add(mProduct);
		
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
			
		}
		
		
		return "redirect:/manage/products?operation=product";
	}

	
	
	
	
	// returning categories for all the request
	@ModelAttribute("categories")
	public List<Category> getCategory() {
		return categoryDAO.list();
	}
}
