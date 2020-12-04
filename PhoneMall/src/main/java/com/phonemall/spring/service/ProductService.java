package com.phonemall.spring.service;

import java.util.List;

import com.phonemall.spring.domain.ProductVO;

public interface ProductService {
	public void register(ProductVO product);
	
	public ProductVO get(Long product_id);
	
	public boolean modify(ProductVO product);
	
	public boolean remove(Long product_id);
	
	public List<ProductVO> getList();
}
