package net.kzn.onlineshopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.kzn.onlineshopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result) {
		ModelAndView mv = new ModelAndView("page");
		if (result != null) {
			switch (result) {
			case "added":
				mv.addObject("message", "Product has been Added successfully");
				break;
			case "updated":
				mv.addObject("message", "CardLine has been updated successfully");
				break;
			case "error":
				mv.addObject("message", "Something went wrong!");
				break;
			case "deleted":
				mv.addObject("message", "CardLine has been Deleted successfully");
				break;
			case "maximum":
				mv.addObject("message", "Maximum limit for the item has been reached!");
				break;
			case "unavailable":
				mv.addObject("message", "Product quantity is not available!");
				break;

			}
		}
		mv.addObject("title", "Shopping Cart");
		mv.addObject("cartLines", cartService.getCartLines());
		mv.addObject("userClickShowCart", true);
		return mv;
	}

	@RequestMapping("/add/{productId}/product")
	public String addCartLine(@PathVariable int productId) {
		String response = cartService.addCartLine(productId);

		return "redirect:/cart/show?" + response;
	}

	@RequestMapping("/{cartLineId}/update")
	public String updateCartLine(@PathVariable int cartLineId, @RequestParam int count) {
		String response = cartService.updateCartLine(cartLineId, count);
		return "redirect:/cart/show?" + response;
	}

/*	@RequestMapping("/{cartLineId}/update")
	public String udpateCartLine(@PathVariable int cartLineId, @RequestParam int count) {
		String response = cartService.manageCartLine(cartLineId, count);
		return "redirect:/cart/show?" + response;
	}
*/
	@RequestMapping("/{cartLineId}/delete")
	public String deleteCartLine(@PathVariable int cartLineId) {
		String response = cartService.deleteCartLine(cartLineId);
		return "redirect:/cart/show?" + response;
	}

}
