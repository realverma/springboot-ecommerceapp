package org.jsp.merchantbootapp.controller;

import java.util.List;

import org.jsp.merchantbootapp.model.Merchant;
import org.jsp.merchantbootapp.model.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/{merchant_id}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product,
			@PathVariable(name = "merchant_id") int merchant_id) {
		return productService.saveProduct(product, merchant_id);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseStructure<List<Product>> findAll() {
		return productService.findAll();
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable(name = "id") int id) {
		return productService.findById(id);
	}

	@GetMapping("/by-brand/{brand}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByBrand(@PathVariable(name = "brand") String brand) {
		return productService.findByBrand(brand);
	}

	@GetMapping("/by-category/{category}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByCategory(
			@PathVariable(name = "category") String category) {
		return productService.findByCategory(category);
	}

	@GetMapping("/by-merchant/{merchant_id}")
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchant(
			@PathVariable(name = "merchant_id") int merchant_id) {
		return productService.findByMerchantId(merchant_id);
	}

	@PostMapping("/by-merchant")
	public ResponseEntity<ResponseStructure<List<Product>>> findByMerchant(@RequestParam long phone,
			@RequestParam String password) {
		return productService.findByMerchant(phone, password);
	}
}
