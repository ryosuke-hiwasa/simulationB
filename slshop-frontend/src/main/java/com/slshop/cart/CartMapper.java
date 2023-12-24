package com.slshop.cart;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.slshop.common.entity.CartItem;
import com.slshop.common.entity.Customer;
import com.slshop.common.entity.product.Product;

@Mapper
public interface CartMapper {
	public List<CartItem> findAll();
	public void insert(Integer customerId,Integer productId,int quantity);
	public Product getProduct();
	public Customer getCustomer();
	
}
