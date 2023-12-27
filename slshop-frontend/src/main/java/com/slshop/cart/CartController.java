package com.slshop.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.slshop.common.entity.CartItem;
import com.slshop.common.entity.product.Product;
import com.slshop.product.ProductService;
import com.slshop.security.CustomerUserDetails;

@Controller
@RequestMapping("/cart")
public class CartController {
	private final CartService cartService;
	private final ProductService productService;
	

	@Autowired
	public CartController(CartService cartService,ProductService productService) {
		this.cartService = cartService;
		this.productService = productService;
	}

	@GetMapping
	public String viewCart(Model model, @AuthenticationPrincipal CustomerUserDetails userDetails,
			@ModelAttribute("message") String message) {
		List<CartItem> cartItem = this.cartService.findAll(userDetails.getId());
		List<Product> product = this.productService.getProducts();
		model.addAttribute("cartItem", cartItem);
		model.addAttribute("product",product);
		return "cart/cart";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		cartService.delete(id);
		return "redirect:/cart";
	}

}
