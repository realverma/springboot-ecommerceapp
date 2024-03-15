package org.jsp.merchantbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.model.Merchant;
import org.jsp.merchantbootapp.model.Product;
import org.jsp.merchantbootapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}

	public List<Product> findByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

	public List<Product> findByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	public List<Product> findByMerchantId(int merchant_id) {
		return productRepository.findByMerchantId(merchant_id);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}
	public List<Product> findByMerchant(long phone, String password) {
		return productRepository.findByMerchantPhone$Password(phone, password);
	}
}
