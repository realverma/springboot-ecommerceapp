package org.jsp.merchantbootapp.dao;

import org.jsp.merchantbootapp.model.Address;
import org.jsp.merchantbootapp.model.Product;
import org.jsp.merchantbootapp.model.User;
import org.jsp.merchantbootapp.repository.AddressRepository;
import org.jsp.merchantbootapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressDao {
    @Autowired
    private AddressRepository addressRepository;

    public Optional<Address> findById(int id) {
        return addressRepository.findById(id);
    }
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
}
