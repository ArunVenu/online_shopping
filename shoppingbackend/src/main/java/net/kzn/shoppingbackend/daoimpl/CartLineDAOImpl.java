package net.kzn.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.CartLineDAO;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.CartLine;

@Repository("cartLineDAO")
@Transactional
public class CartLineDAOImpl implements CartLineDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CartLine get(int id) {

		return sessionFactory.getCurrentSession().get(CartLine.class, id);
	}

	@Override
	public boolean add(CartLine cartline) {

		try {
			sessionFactory.getCurrentSession().persist(cartline);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		
	}
	
	
	@Override
	public boolean update(CartLine cartline) {

		try {
			sessionFactory.getCurrentSession().update(cartline);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		
	}

	
	@Override
	public boolean delete(CartLine cartline) {

		try {
			sessionFactory.getCurrentSession().delete(cartline);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<CartLine> list(int cartId) {
		
		String query = "FROM CartLine WHERE cartId = :cardId";
		return sessionFactory.getCurrentSession()
					.createQuery(query, CartLine.class)
							.setParameter("cardId", cartId)
										.getResultList();
		
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cardId AND available = :available";
		return sessionFactory.getCurrentSession()
					.createQuery(query, CartLine.class)
							.setParameter("cardId", cartId)
								.setParameter("available", true)
										.getResultList();
								
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String query = "FROM CartLine WHERE cartId = :cardId AND product.id = :productId";
		try {
			return sessionFactory.getCurrentSession()
					.createQuery(query, CartLine.class)
							.setParameter("cardId", cartId)
								.setParameter("productId", productId)
										.getSingleResult();
		}
		
		catch(Exception e) {
			return null;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {

		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
}
