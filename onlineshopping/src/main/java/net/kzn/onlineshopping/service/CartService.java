package net.kzn.onlineshopping.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kzn.onlineshopping.model.UserModel;
import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.Product;

@Service("cartService")
public class CartService {

	@Autowired
	private CartLineDAO cartlineDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private HttpSession session;

	// returns the cart of the user who has logged in
	private Cart getCart() {
		return ((UserModel) session.getAttribute("userModel")).getCart();
	}

	public String addCartLine(int productId) {
		Cart cart = this.getCart();
		String response = null;
		CartLine cartLine = cartlineDAO.getByCartAndProduct(cart.getId(), productId);
		if (cartLine == null) {
			// add a new cartLine if a new product is getting added
			cartLine = new CartLine();
			Product product = productDAO.get(productId);
			
			// transfer the product details to cartLine
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setProductCount(1);
			cartLine.setBuyingPrice(product.getUnitprice());
			cartLine.setTotal(product.getUnitprice());

			// insert a new cartLine
			cartlineDAO.add(cartLine);
			
	
			// update the cart
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() + 1);
	        cartlineDAO.updateCart(cart);

						
			response = "result=added";
			
		} else {
			// check if the cartLine has been already reached to maximum count
			if (cartLine.getProductCount() < 3) {
				// call the manageCartLine method to increase the count
				response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount() + 1);
			} else {
				response = "result=maximum";
			}
		}
		return response;
	}

	// returns the entire cart lines
	public List<CartLine> getCartLines() {
		return cartlineDAO.list(this.getCart().getId());
	}

	/* to update the cart count */
	public String manageCartLine(int cartLineId, int count) {
		
		CartLine cartLine = cartlineDAO.get(cartLineId);		

		double oldTotal = cartLine.getTotal();

		
		Product product = cartLine.getProduct();
		
		// check if that much quantity is available or not
		if(product.getQuantity() < count) {
			return "result=unavailable";		
		}	
		
		// update the cart line
		cartLine.setProductCount(count);
		cartLine.setBuyingPrice(product.getUnitprice());
		cartLine.setTotal(product.getUnitprice() * count);
		cartlineDAO.update(cartLine);

	
		// update the cart
		Cart cart = this.getCart();
		cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
		cartlineDAO.updateCart(cart);
		
		return "result=updated";
	}

	
	//update the cart line
	public String updateCartLine(int cartLineId, int count) {
		
		//fetch the cart Line
		
		CartLine cartline = cartlineDAO.get(cartLineId);
		
		if(cartline == null) {
			
			return "result=error";
			
		}
		else {
			
			Product product = cartline.getProduct();
			double oldtotal = cartline.getTotal();	
			//to check wether quantity is available or not for the count
			if(product.getQuantity() <= count) {
				count = product.getQuantity();
			}
			cartline.setProductCount(count);
			cartline.setBuyingPrice(product.getUnitprice());
			cartline.setTotal(product.getUnitprice() * count);
			//call the DAO to save the method
			cartlineDAO.update(cartline);
			//update the cart
			//1.get the cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldtotal + cartline.getTotal());
			//2.update the cart
			cartlineDAO.updateCart(cart);
			return "result=updated";
		}
		
		
	}

	public String deleteCartLine(int cartLineId) {
		
		//fetch the cart Line
		CartLine cartLine = cartlineDAO.get(cartLineId);
		
		if(cartLine == null) {
			return "result=error";			
		}else {
			
			//update the cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartlineDAO.updateCart(cart);
	
			//remove the cart line
			
			cartlineDAO.delete(cartLine);
			
			return "result=deleted";
			
		}
		
	}
}
