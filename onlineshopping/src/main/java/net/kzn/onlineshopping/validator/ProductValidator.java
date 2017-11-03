package net.kzn.onlineshopping.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.kzn.shoppingbackend.dto.Product;

//To call this class for explicitly validate

public class ProductValidator implements  Validator {

	
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		Product product = (Product) target;
		
		//whether file selected or not 
		
		if(product.getFile() ==null || product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Please select an image file to upload!");
			return;
		}

		//
		if(
		!(product.getFile().getContentType().equals("image/jpeg"))||
		product.getFile().getContentType().equals("image/png") || 
		product.getFile().getContentType().equals("image/png")) 
		
		{
			errors.rejectValue("file", null, "Please use only image file to upload!");
			
		}
		
	}



}
