package org.jsp.merchantbootapp.service;

import org.jsp.merchantbootapp.dao.UserDao;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.InvalidCredentialsException;
import org.jsp.merchantbootapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserDao userDao;

    public ResponseStructure<User> saveUser(User user) {
        ResponseStructure<User> structure = new ResponseStructure<>();
        structure.setMessage("User saved");
        structure.setData(userDao.saveUser(user));
        structure.setStatusCode(HttpStatus.CREATED.value());
        return structure;
    }

    public ResponseStructure<List<User>> findAll() {
        ResponseStructure<List<User>> structure = new ResponseStructure<>();
        structure.setMessage("List of Users");
        structure.setData(userDao.findAll());
        structure.setStatusCode(HttpStatus.OK.value());
        return structure;
    }

    public ResponseEntity<ResponseStructure<User>> verifyUser(
            long phone, String password) {
        Optional<User> recUser = userDao.verify(phone, password);
        ResponseStructure<User> structure = new ResponseStructure<>();
        if (recUser.isPresent()) {
            structure.setMessage("Verification Succesfull");
            structure.setData(recUser.get());
            structure.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<ResponseStructure<User>>
                    (structure, HttpStatus.OK);
        }
        throw new InvalidCredentialsException
                ("Invalid Phone Number or Password");
    }
}
