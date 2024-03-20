package org.jsp.merchantbootapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private long phone;
    @Column(nullable = false)
    private int pincode;
    @Column(nullable = false)
    private String locality;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String landmark;
    @Column(nullable = false, unique = true)
    private long alternate_phone;
    @Column(nullable = false)
    private String address_type;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
