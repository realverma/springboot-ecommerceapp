package org.jsp.merchantbootapp.controller;

import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.model.Address;
import org.jsp.merchantbootapp.model.Product;
import org.jsp.merchantbootapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/{user_id}")
    public ResponseEntity<ResponseStructure<Address>> saveAddress(@RequestBody Address address,
                                                                  @PathVariable(name = "user_id") int user_id) {
        return addressService.saveAddress(address, user_id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Address>> findById(@PathVariable(name = "id") int id) {
        return addressService.findById(id);
    }

    @PutMapping
    public ResponseEntity<ResponseStructure<Address>> updateAddress(@RequestBody Address address) {
        return addressService.updateAddress(address);
    }
}
