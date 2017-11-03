package net.kzn.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.kzn.shoppingbackend.dao.CategoryDAO;
import net.kzn.shoppingbackend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private static Category category;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.kzn.shoppingbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}
	
	//Add single Category
	/*@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("Computer");
		category.setDescription("Description about the Computer");
		category.setImageUrl("img_001.png");
		assertEquals("Sucessfully added a category inside a table",true,categoryDAO.add(category));
		
	}*/
	
	//Get single Category
	/*@Test
	public void testGetCategory() {
		category = categoryDAO.get(1);
		assertEquals("Sucessfully fetched a single category from a table","Computer",category.getName());
	}*/
	
/*	//Update single Category
		@Test
		public void testupdateCategory() {
			category = categoryDAO.get(1);
			category.setName("TV");
			assertEquals("Sucessfully Updated a single category insert a table",true,categoryDAO.update(category));
		}
		
	*/	
	
	/*//Delete single Category
			@Test
			public void testupdateCategory() {
				category = categoryDAO.get(1);
				category.setName("Computer");
				assertEquals("Sucessfully deleted a single category a table",true,categoryDAO.delete(category));
			}*/
		/*	//fetch list of active products
			@Test
			public void testlistCategory() {
				
				assertEquals("Sucessfully fetched a single category a table",2,categoryDAO.list().size());
			}*/
	
	//TEST CRUD application
	
	@Test
	public void testCRUDCategory() {
		
		//add category
		category = new Category();
		category.setName("TV");
		category.setDescription("Description about the Computer");
		category.setImageUrl("img_001.png");
		assertEquals("Sucessfully added a category inside a table",true,categoryDAO.add(category));
	
		category = new Category();
		category.setName("Computer");
		category.setDescription("Description about the Tv");
		category.setImageUrl("img_002.png");
		assertEquals("Sucessfully added a category inside a table",true,categoryDAO.add(category));
	
		//fetching and updating the category
		category = categoryDAO.get(2);
		assertEquals("Sucessfully fetched a single category from a table","Computer",category.getName());
		
		//delete record
		assertEquals("Sucessfully deleted a single category a table",true,categoryDAO.delete(category));
		
		//fetching list
		assertEquals("Sucessfully fetched a single category a table",1,categoryDAO.list().size());
		
	}
	
	
}
