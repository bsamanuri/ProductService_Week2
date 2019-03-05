package com.epam.training.productservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.training.productservice.model.Product;
import com.epam.training.productservice.repository.ProductRepository;



@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(long id) {
		return productRepository.findById(id);
	}

	@Override
	public boolean deleteProductById(long id) {
		productRepository.deleteById(id);
		return productRepository.existsById(id);
	}

	@Override
	public Product saveProduct(Product product) {
		productRepository.save(product);
		return product;
	}

	@Override
	public Product saveOrupdateProduct(long id, Product product) {


		Optional<Product> updateProd = productRepository.findById(id);
		if (updateProd.isPresent()) {
			updateProd.get().setName(product.getName());
			updateProd.get().setDesc(product.getDesc());
			updateProd.get().setPrice(product.getPrice());
			productRepository.save(updateProd.get());
		}else {
			productRepository.save(product);
		}
		return product;
	}
	
}
