package com.emp.manage.enitity;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users", schema = "login_app")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;


    }