package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;


import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;
import net.kzn.shoppingbackend.dto.Product;
import net.kzn.shoppingbackend.dto.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class CartLineTestCase {

	

	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	
	private CartLine cartLine = null;
	private User user = null;
	private Product product = null;
	private Cart cart = null;
	
	

		@BeforeClass
		public static void init() {
			context = new AnnotationConfigApplicationContext();
			context.scan("net.kzn.shoppingbackend");
			context.refresh();
			cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
			productDAO = (ProductDAO)context.getBean("productDAO");
			userDAO = (UserDAO)context.getBean("userDAO");
			}

	
	@Test
	public void testAddNewCartLine() {
		
		//1.get the user
		
		user = userDAO.getByEmail("user@gmail.com");
		
		System.out.println(user);
		
		//2.fetch the cart
		
		cart = user.getCart();
		
		System.out.println(cart);
		
		//3. get the product
		
		product = productDAO.get(1);
		
		System.out.println(product);
		
		//4. create cartLine
		
		cartLine = new CartLine();
		
		cartLine.setBuyingPrice(product.getUnitprice());
		
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitprice());
		
		cartLine.setAvailable(true);
		
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		assertEquals("Failed to add the cartLine", true, cartLineDAO.add(cartLine));
	
		//update the cart
		
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
		assertEquals("Failed to add the cart", true, cartLineDAO.updateCart(cart));
		
		
		
		
	}
	
	
}
