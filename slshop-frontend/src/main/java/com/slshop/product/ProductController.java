package com.slshop.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.slshop.cart.CartService;
import com.slshop.common.entity.CartItem;
import com.slshop.common.entity.Customer;
import com.slshop.common.entity.product.Product;

@Controller
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	private final CartService cartService;

	@Autowired
	public ProductController(ProductService productService, CartService cartService) {
		this.productService = productService;
		this.cartService = cartService;
	}

	@GetMapping
	public String viewProducts(Model model) {
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		return "products/products";
	}

	@GetMapping("/detail/{productId}")
	public String viewProductDetail(@PathVariable("productId") Long productId, Model model) {
		Product product = productService.getProduct(productId);
		model.addAttribute("id", productId);
		model.addAttribute("product", product);
		return "products/product_detail";
	}

	@PostMapping("/detail/{id}")
	public String addCart(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Integer productid,
			@RequestParam("quantity") int quantity,Model model) {
		Integer test =10;
		
		this.cartService.insert(((Customer)userDetails).getId(),productid, quantity);
		List<CartItem> cartItem = this.cartService.findAll();
		model.addAttribute("cartItem",cartItem);
		return "/cart/cart";
	}
}
