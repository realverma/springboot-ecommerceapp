package org.jsp.merchantbootapp.dao;

import org.jsp.merchantbootapp.model.User;
import org.jsp.merchantbootapp.model.User;
import org.jsp.merchantbootapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public java.util.List<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public Optional<User> verify(long phone, String password) {
        return userRepository.verify(phone, password);
    }
}
