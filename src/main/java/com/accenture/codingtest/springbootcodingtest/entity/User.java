package com.accenture.codingtest.springbootcodingtest.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

import java.util.UUID;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="User")
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private UUID id;
	
    @Column(name = "username" , unique=true)
    @NotBlank(message = "Required Field")
    private String username ;    
    
    @Column(name = "password")
    @NotBlank(message = "Required Field")
    private String password;

	public User() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
        	
    
	
}
