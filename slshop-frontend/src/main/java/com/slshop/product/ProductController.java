package com.slshop.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.slshop.cart.CartService;
import com.slshop.common.entity.product.Product;
import com.slshop.security.CustomerUserDetails;

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
	public String addCart(@AuthenticationPrincipal CustomerUserDetails userDetails, @PathVariable("id") Long productId,
			@RequestParam("quantity") int quantity, RedirectAttributes ra) {

		if (this.cartService.getQuantity(productId) <= 0) {
			this.cartService.insert(userDetails.getId(), productId, quantity);
		}

		if (this.cartService.getQuantity(productId) + quantity < 10) {
			ra.addFlashAttribute("message",
					"カートに商品を追加できませんでした｡最大数量は10個です(カート内:" + this.cartService.getQuantity(productId) + "個)");
			return "redirect:/cart";
		}

		if (this.cartService.checkItem(userDetails.getId(), productId)) {
			this.cartService.addQuan(userDetails.getId(), productId, quantity);
		}
		ra.addFlashAttribute("message", "カートに商品を追加しました(" + quantity + ")個");
		return "redirect:/cart";
	}
}
