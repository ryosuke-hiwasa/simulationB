package com.slshop.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.slshop.common.entity.product.Product;

@Mapper
public interface ProductMapper {

    List<Product> findAll();

    Product findById(Long id);


}
