package com.slshop.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.slshop.common.entity.CartItem;

@Controller
@RequestMapping("/cart")
public class CartController {
	private final CartService cartService;
	
	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping
	public String viewCart(Model model) {
		List<CartItem> cartItem = this.cartService.findAll();
		model.addAttribute("cartItem",cartItem);
		return "cart/cart";
	}
	
	@GetMapping("/insert/{quantity}")
	public String insert(@PathVariable("quantity") Integer quantity) {
		this.cartService.insert(quantity);
		return "redirect:/cart";
	}
}
