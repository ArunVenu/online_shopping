package net.kzn.onlineshopping.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import net.kzn.onlineshopping.model.RegisterModel;
import net.kzn.shoppingbackend.dao.UserDAO;
import net.kzn.shoppingbackend.dto.Address;
import net.kzn.shoppingbackend.dto.Cart;
import net.kzn.shoppingbackend.dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;

	// to encrypt password

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public RegisterModel init() {
		return new RegisterModel();
	}

	public void addUser(RegisterModel registerModel, User user) {

		registerModel.setUser(user);

	}

	public void addBilling(RegisterModel registerModel, Address billing) {

		registerModel.setBilling(billing);
	}

	// checking the password match field
	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";
		if ((!user.getPassword().equals(user.getConfirmPassword()))) {

			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Password does not match the confirm password!").build());

			transitionValue = "failure";

		}

		// check the uniqueness of the userId/Email

		if (userDAO.getByEmail(user.getEmail()) != null) {
			error.addMessage(
					new MessageBuilder().error().source("email").defaultText("Email I'd is already present!").build());
			transitionValue = "failure";

		}

		return transitionValue;
	}

	//

	public String saveAll(RegisterModel registerModel) {
		String transitionValue = "success";
		User user = registerModel.getUser();
		if (user.getRole().equals("USER")) {
			// create a new cart
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}

		// encode the password

		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		
		// save the user
		userDAO.addUser(user);
		
		
		// save the billing address
		Address billing = registerModel.getBilling();
		billing.setUserId(user.getId());
		billing.setBilling(true);
		userDAO.addAddress(billing);
		return transitionValue;
	}
}
