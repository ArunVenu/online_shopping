package net.kzn.shoppingbackend.dao;

import java.util.List;

import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;

public interface CartLineDAO {

	// the common methods from previously coded one

	public CartLine get(int id);

	public boolean add(CartLine cartline);

	public boolean update(CartLine cartline);

	public boolean delete(CartLine cartLine);

	public List<CartLine> list(int cartId);

	// other business method related to the cart Lines

	public List<CartLine> listAvailable(int cartId);

	public CartLine getByCartAndProduct(int cartId, int productId);

	// add an Cart
	public boolean updateCart(Cart cart);


	

}
