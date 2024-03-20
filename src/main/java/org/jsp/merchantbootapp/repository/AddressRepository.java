package org.jsp.merchantbootapp.repository;

import org.jsp.merchantbootapp.model.Address;
import org.jsp.merchantbootapp.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
