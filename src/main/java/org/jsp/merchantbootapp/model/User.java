package org.jsp.merchantbootapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private long phone;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String gender;
    @OneToMany(mappedBy = "user")
    private List<Address> addresses;

    @OneToMany
    private List<Product> cart;
    @JoinTable(name = "user_wishlist", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "product_id") })
    @OneToMany
    private List<Product> wishList;
}
