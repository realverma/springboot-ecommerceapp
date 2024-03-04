package org.jsp.merchantbootapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.merchantbootapp.model.Merchant;
import org.jsp.merchantbootapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository merchantRepository;

	public Merchant saveMerchant(Merchant merchant) {
		return merchantRepository.save(merchant);
	}

	public Optional<Merchant> findById(int id) {
		return merchantRepository.findById(id);
	}

	public List<Merchant> findAll() {
		return merchantRepository.findAll();
	}

	public List<Merchant> findByName(String name) {
		return merchantRepository.findByName(name);
	}

	public boolean deleteById(int id) {
		Optional<Merchant> recMerchant = findById(id);
		if (recMerchant.isPresent()) {
			merchantRepository.delete(recMerchant.get());
			return true;
		}
		return false;
	}

	public Optional<Merchant> verify(long phone, String password) {
		return merchantRepository.verify(phone, password);
	}
}
