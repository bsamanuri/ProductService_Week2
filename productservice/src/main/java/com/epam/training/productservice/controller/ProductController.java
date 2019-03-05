package com.epam.training.productservice.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.epam.training.productservice.controller.exception.ProductNotFoundException;
import com.epam.training.productservice.model.Product;
import com.epam.training.productservice.service.ProductService;


@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts()
	{
		return ResponseEntity.ok(productService.getProducts());
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> saveProducts(@RequestBody Product product)
	{
		productService.saveProduct(product);
		URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(location).body(product);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product>  getProductByID(@PathVariable int id){
		return ResponseEntity.ok(productService.getProductById(id).orElseThrow(()-> new ProductNotFoundException("Product ID -"+id)));
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<Product> saveOrupdateProductById(@PathVariable int id, @RequestBody Product product)
	{
		return ResponseEntity.ok(productService.saveOrupdateProduct(id,product));
	}
	
	@DeleteMapping("/products/{id}")
	public boolean deleteProductById(@PathVariable long id)
	{
		return productService.deleteProductById(id);
	}
}
