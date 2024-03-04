package org.jsp.merchantbootapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MerchantRepository extends JpaRepository<Merchant, Integer> {
	@Query("select m from Merchant m where m.phone=?1 and m.password=?2")
	public Optional<Merchant> verify(long phone, String password);

	@Query("select m from Merchant m where m.name=?1")
	public List<Merchant> findByName(String name);
}
