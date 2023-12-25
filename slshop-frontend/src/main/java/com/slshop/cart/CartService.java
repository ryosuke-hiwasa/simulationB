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

	public List<CartItem> findAll(Long customerId) {
		return cartMapper.findAll(customerId);
	}

	public void insert(Long customerId,Long productId, int quantity) {
		this.cartMapper.insert(customerId,productId, quantity);
	};
	
	public void addQuan(Long customerId,Long productId, int quantity) {
		this.cartMapper.addQuan(customerId,productId, quantity);
	};
	
	public boolean checkItem(Long customerId,Long productId) {
		
		return this.cartMapper.checkItem(customerId, productId);
	}
	
	public void delete(Integer id) {
		this.cartMapper.deleteById(id);
	}
	
}
