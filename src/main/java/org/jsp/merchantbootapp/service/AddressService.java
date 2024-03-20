package org.jsp.merchantbootapp.service;

import org.jsp.merchantbootapp.dao.AddressDao;
import org.jsp.merchantbootapp.dao.UserDao;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.AddressNotFoundException;
import org.jsp.merchantbootapp.exception.IdNotFoundException;
import org.jsp.merchantbootapp.model.Address;
import org.jsp.merchantbootapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressDao addressDao;
    @Autowired
    private UserDao userDao;

    public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address, int user_id) {
        Optional<User> recUser = userDao.findById(user_id);
        ResponseStructure<Address> structure = new ResponseStructure<>();
        if (recUser.isPresent()) {
            User user = recUser.get();
            user.getAddresses().add(address);
            address.setUser(user);
            structure.setData(addressDao.saveAddress(address));
            structure.setMessage("Address added");
            structure.setStatusCode(HttpStatus.CREATED.value());
            return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
        }
        throw new IdNotFoundException();
    }

    public ResponseEntity<ResponseStructure<Address>> findById(int id) {
        Optional<Address> recAddress = addressDao.findById(id);
        ResponseStructure<Address> structure = new ResponseStructure<>();
        if (recAddress.isPresent()) {
            structure.setData(recAddress.get());
            structure.setMessage("Address Found");
            structure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
        }
        throw new AddressNotFoundException("Invalid Address Id");
    }

    public ResponseEntity<ResponseStructure<Address>> updateAddress(Address address) {
        Optional<Address> recAddress = addressDao.findById(address.getId());
        ResponseStructure<Address> structure = new ResponseStructure<>();
        if (recAddress.isPresent()) {
            Address dbAddress = recAddress.get();
            dbAddress.setAddress(address.getAddress());
            dbAddress.setCity(address.getCity());
            dbAddress.setLandmark(address.getLandmark());
            dbAddress.setLocality(address.getLocality());
            dbAddress.setPincode(address.getPincode());
            dbAddress.setState(address.getState());
            dbAddress.setPhone(address.getPhone());
            dbAddress.setAlternate_phone(address.getAlternate_phone());
            dbAddress.setName(address.getName());
            dbAddress.setAddress_type(address.getAddress_type());
            structure.setData(addressDao.saveAddress(dbAddress));
            structure.setMessage("Address Updated");
            structure.setStatusCode(HttpStatus.ACCEPTED.value());
            return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.ACCEPTED);
        }
        throw new AddressNotFoundException("Cannot Update as Address Id is Invalid");
    }
}
