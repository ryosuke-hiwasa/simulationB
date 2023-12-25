package com.slshop.cart;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.slshop.common.entity.CartItem;
import com.slshop.common.entity.Customer;
import com.slshop.common.entity.product.Product;

@Mapper
public interface CartMapper {
	public List<CartItem> findAll(Long customerId);
	public void insert(Long customerId,Long productId,int quantity);
	public Product getProduct();
	public Customer getCustomer();
	public void deleteById(Integer id);
	public boolean checkItem(Long customerId,Long productId);
	public void addQuan(Long customerId,Long productId,int quantity);
}
