package org.jsp.merchantbootapp.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.jsp.merchantbootapp.model.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/merchants")
public class MerchantController {
	@Autowired
	private MerchantService merchantService;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseStructure<Merchant> saveMerchant(@RequestBody Merchant merchant, HttpServletRequest request) {
		return merchantService.saveMerchant(merchant, request);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant) {
		return merchantService.updateMerchant(merchant);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> findById(@PathVariable(name = "id") int id) {
		return merchantService.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name = "id") int id) {
		return merchantService.deleteById(id);
	}

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseStructure<List<Merchant>> findAll() {
		return merchantService.findAll();
	}

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam long phone,
			@RequestParam String password) {
		return merchantService.verifyMerchant(phone, password);
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(@PathVariable String name) {
		return merchantService.findByName(name);
	}

	@GetMapping("/activate")
	public ResponseEntity<ResponseStructure<String>> activate(@RequestParam String token){
		return merchantService.activate(token);
	}






}
