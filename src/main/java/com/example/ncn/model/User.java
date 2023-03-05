package com.example.ncn.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private String  uid;

     private String  name;

     private String  surname;

     private Date birthday;

    private String  email;
    private String  password;
    private enum role;
    private boolean banned;
}
