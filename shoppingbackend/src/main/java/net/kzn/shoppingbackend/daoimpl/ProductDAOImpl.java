package net.kzn.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.kzn.shoppingbackend.dao.ProductDAO;
import net.kzn.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public Product get(int productId) {
		return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
	}

	// List
	@Override
	public List<Product> list() {

		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	// Insert
	@Override
	public boolean add(Product product) {

		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// update method
	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// delete
	@Override
	public boolean delete(Product product) {

		product.setActive(false);

		return this.update(product);
	}

	
	//listActiveProducts
	@Override
	public List<Product> listActiveProducts() {
		String SelectActiveProducts = "FROM Product WHERE active = :active";
		return sessionFactory.getCurrentSession().createQuery(SelectActiveProducts,Product.class).setParameter("active", true).getResultList();
	}

	//listActiveProductsByCategory
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String SelectActiveProductsByCategory = "FROM Product WHERE active = :active AND categoryId =:categoryId";
		return sessionFactory
				.getCurrentSession()
					.createQuery(SelectActiveProductsByCategory)
						.setParameter("active", true)
						.setParameter("categoryId",categoryId)
							.getResultList();					
	}

	//Latest Active Product
	@Override
	public List<Product> getLatestActiveProducts(int count) {
		
		return sessionFactory
					.getCurrentSession()
						.createQuery("FROM PRODUCT WHERE active =:active ORDER BY id",Product.class)
							.setParameter("active", true)
								.setFirstResult(0)
									.setMaxResults(count)
										.getResultList();
		
	}

}
