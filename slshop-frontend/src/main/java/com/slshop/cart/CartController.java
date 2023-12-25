package com.slshop.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.slshop.common.entity.CartItem;
import com.slshop.security.CustomerUserDetails;

@Controller
@RequestMapping("/cart")
public class CartController {
	private final CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping
	public String viewCart(Model model,@AuthenticationPrincipal CustomerUserDetails userDetails) {
		List<CartItem> cartItem = this.cartService.findAll(userDetails.getId());
		model.addAttribute("cartItem",cartItem);
		return "cart/cart";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		cartService.delete(id);
		return "redirect:/cart";
	}
}
