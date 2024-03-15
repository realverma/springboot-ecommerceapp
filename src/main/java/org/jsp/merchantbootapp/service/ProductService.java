package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dao.ProductDao;
import org.jsp.merchantbootapp.model.Merchant;
import org.jsp.merchantbootapp.model.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.IdNotFoundException;
import org.jsp.merchantbootapp.exception.InvalidCredentialsException;
import org.jsp.merchantbootapp.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MerchantDao merchantDao;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int merchant_id) {
		Optional<Merchant> recMerchant = merchantDao.findById(merchant_id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			Merchant merchant = recMerchant.get();
			merchant.getProducts().add(product);
			product.setMerchant(merchant);
			structure.setData(productDao.saveProduct(product));
			structure.setMessage("Product added");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		Optional<Product> recProduct = productDao.findById(product.getId());
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			Product dbProduct = recProduct.get();
			dbProduct.setBrand(product.getBrand());
			dbProduct.setCategory(product.getCategory());
			dbProduct.setCost(product.getCost());
			dbProduct.setDescription(product.getDescription());
			dbProduct.setImage_url(product.getImage_url());
			dbProduct.setName(product.getName());
			structure.setData(productDao.saveProduct(dbProduct));
			structure.setMessage("Product Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		throw new ProductNotFoundException("Cannot Update as Product Id is Invalid");
	}

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		Optional<Product> recProduct = productDao.findById(id);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		if (recProduct.isPresent()) {
			structure.setData(recProduct.get());
			structure.setMessage("Product Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("Invalid Product Id");
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(String brand) {
		List<Product> products = productDao.findByBrand(brand);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("List of products with the brand:" + brand);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("No Product for the  brand " + brand);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(String category) {
		List<Product> products = productDao.findByCategory(category);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("List of products with the category:" + category);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("No Product for the  category " + category);
	}

	public ResponseStructure<List<Product>> findAll() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setMessage("List of Products");
		structure.setData(productDao.findAll());
		structure.setStatusCode(HttpStatus.OK.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchantId(int id) {
		List<Product> products = productDao.findByMerchantId(id);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("List of products added by Merchant:" + id);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new ProductNotFoundException("No Products found for Merchant id:" + id);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchant(long phone, String password) {
		List<Product> products = productDao.findByMerchant(phone, password);
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		if (products.size() > 0) {
			structure.setData(products);
			structure.setMessage("List of products added by Merchant");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException("No Products for entered phone and password");
	}


}
