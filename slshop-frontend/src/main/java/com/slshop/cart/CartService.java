package com.slshop.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slshop.common.entity.CartItem;

@Service
public class CartService {
	private final CartMapper cartMapper;

	@Autowired
	public CartService(CartMapper cartMapper) {
		this.cartMapper = cartMapper;
	}

	public List<CartItem> findAll() {
		return cartMapper.findAll();
	}

	public void insert(Long customerId,Integer productId, int quantity) {
		this.cartMapper.insert(customerId,productId, quantity);
	};
	
//	public boolean cartCheck(Long customerId,Integer productId) {
//		
//		return this.cartMapper.cartCheck(customerId, productId);
//	}
	
	public void delete(Integer id) {
		this.cartMapper.deleteById(id);
	}
	
}
