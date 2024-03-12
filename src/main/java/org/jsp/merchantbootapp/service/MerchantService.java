package org.jsp.merchantbootapp.service;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.exception.MerchantNotFoundException;
import org.jsp.merchantbootapp.model.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.IdNotFoundException;
import org.jsp.merchantbootapp.exception.InvalidCredentialsException;
import org.jsp.merchantbootapp.util.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantDao;

	@Autowired
	private ECommerceAppEmailService emailService;

	public ResponseStructure<Merchant> saveMerchant(Merchant merchant, HttpServletRequest request) {
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		merchant.setStatus(AccountStatus.IN_ACTIVE.toString());
		merchant.setToken(RandomString.make(45));
		structure.setBody(merchantDao.saveMerchant(merchant));
		String message=emailService.sendWelcomeMail(merchant,request);
		structure.setMessage("Merchant saved"+","+message);
		structure.setData(merchantDao.saveMerchant(merchant));
		structure.setStatusCode(HttpStatus.CREATED.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant) {
		Optional<Merchant> recMerchant = merchantDao.findById(merchant.getId());
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			Merchant dbMerchant = recMerchant.get();
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setPhone(merchant.getPhone());
			dbMerchant.setPassword(merchant.getPassword());
			structure.setMessage("Merchant Updated");
			structure.setData(merchantDao.saveMerchant(merchant));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Merchant>> findById(int id) {
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setData(recMerchant.get());
			structure.setMessage("Merchant Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		Optional<Merchant> recMerchant = merchantDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Merchant found");
			structure.setData("Merchant deleted");
			structure.setStatusCode(HttpStatus.OK.value());
			merchantDao.deleteById(id);
			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}
		structure.setMessage("Merchant Not found");
		structure.setData("cannot delete merchant as Id is Invalid");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseStructure<List<Merchant>> findAll() {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		structure.setMessage("List of Merchants");
		structure.setData(merchantDao.findAll());
		structure.setStatusCode(HttpStatus.OK.value());
		return structure;
	}

	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(
			long phone, String password) {
		Optional<Merchant> recMerchant = merchantDao.verify(phone, password);
		ResponseStructure<Merchant> structure = new ResponseStructure<>();
		if (recMerchant.isPresent()) {
			structure.setMessage("Verification Succesfull");
			structure.setData(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Merchant>>
			         (structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException
		                       ("Invalid Phone Number or Password");
	}

	public ResponseEntity<ResponseStructure<List<Merchant>>> findByName(String name) {
		ResponseStructure<List<Merchant>> structure = new ResponseStructure<>();
		List<Merchant> merchants = merchantDao.findByName(name);
		structure.setData(merchants);
		if (merchants.size() > 0) {
			structure.setMessage("List of Merchants with entered name ");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure, HttpStatus.OK);
		}
		structure.setMessage("No Merchant found with the entered name ");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Merchant>>>(structure, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ResponseStructure<String>> activate(String token){
		Optional<Merchant> recMerchant=merchantDao.findByToken(token);
		ResponseStructure<String> structure=new ResponseStructure<>();
		if (recMerchant.isPresent()){
			Merchant merchant=recMerchant.get();
			merchant.setStatus(AccountStatus.ACTIVE.toString());
			merchant.setToken(null);
			merchantDao.saveMerchant(merchant);
			structure.setBody("Merchant Found");
			structure.setMessage("Account Verified and Activated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.ACCEPTED);
		}
		throw new MerchantNotFoundException("Invalid URL");
	}

















}
