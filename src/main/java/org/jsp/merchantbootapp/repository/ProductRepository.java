package org.jsp.merchantbootapp.repository;

import java.util.List;

import org.jsp.merchantbootapp.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	public List<Product> findByBrand(String brand);

	public List<Product> findByCategory(String category);

	@Query("select m.products from Merchant m where m.id=?1")
	public List<Product> findByMerchantId(int merchant_id);

	@Query("select m.products from Merchant m where m.phone=?1 and m.password=?2")
	public List<Product> findByMerchantPhone$Password(long phone, String password);

}
