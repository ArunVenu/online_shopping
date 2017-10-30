package net.kzn.shoppingbackend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();

	static {

		// 1 adding the televison
		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("Description about the Television");
		category.setImageUrl("1.jpg");

		categories.add(category);

		// 2 adding the Laptop
		category = new Category();
		category.setId(2);
		category.setName("Laptop");
		category.setDescription("Description about the Laptop");
		category.setImageUrl("2.jpg");

		categories.add(category);

		// 3 adding the Mobile
		category = new Category();
		category.setId(3);
		category.setName("Mobile");
		category.setDescription("Description about the Mobile");
		category.setImageUrl("3.jpg");

		categories.add(category);
	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		//enhanced for loop
		
		for(Category category : categories) {
			if(category.getId() == id) return category;
		}
		return null;
	}

}
