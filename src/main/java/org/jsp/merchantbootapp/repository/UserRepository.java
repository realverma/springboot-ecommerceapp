package org.jsp.merchantbootapp.repository;

import org.jsp.merchantbootapp.model.Merchant;
import org.jsp.merchantbootapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select m from User m where m.phone=?1 and m.password=?2")
    public Optional<User> verify(long phone, String password);

    @Query("select m from User m where m.name=?1")
    public List<User> findByName(String name);
}
