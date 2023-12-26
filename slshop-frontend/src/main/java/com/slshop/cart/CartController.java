package com.slshop.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String viewCart(Model model, @AuthenticationPrincipal CustomerUserDetails userDetails,
			@ModelAttribute("message") String message) {
		List<CartItem> cartItem = this.cartService.findAll(userDetails.getId());
		model.addAttribute("cartItem", cartItem);

		return "cart/cart";
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		cartService.delete(id);
		return "redirect:/cart";
	}

	@PostMapping("/update/{id}")
	public String update(@AuthenticationPrincipal CustomerUserDetails userDetails, @PathVariable("id") Long productId,
			@RequestParam("quantity") int cartQuantity, RedirectAttributes ra) {

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customerId", userDetails.getId());
		paramMap.put("productId", productId);

		int currentQuantity = this.cartService.getQuantity(paramMap);

//		if (currentQuantity > cartQuantity) {
//			this.cartService.updateQuan(userDetails.getId(), productId, cartQuantity);
//		} else if (currentQuantity < cartQuantity && currentQuantity + cartQuantity > 10) {
//			this.cartService.addQuan(userDetails.getId(), productId, cartQuantity);
//		} else {
//			ra.addFlashAttribute("message",
//					"カートに商品を追加できませんでした｡最大数量は10個です(カート内:" + currentQuantity + "個)");
//			return "redirect:/cart";
//		}

		return "redirect:/cart";
	}

}
