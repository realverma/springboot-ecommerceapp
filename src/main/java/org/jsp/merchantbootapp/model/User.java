package org.jsp.merchantbootapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    private int id;
    private String email,name,password;
    private long phone;
}
