package com.accenture.codingtest.springbootcodingtest.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Task")
public class Task {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private UUID id;
	
    @Column(name = "title")
    @NotBlank(message = "Required Field")
    private String title ;    
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "status")
    @NotBlank(message = "Required Field")
    private String status;
    
    @Column(name = "project_id")
    @NotBlank(message = "Required Field")
    private UUID project_id;
    
    @Column(name = "user_id")
    @NotBlank(message = "Required Field")
    private UUID user_id;

	public Task() {
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UUID getProject_id() {
		return project_id;
	}

	public void setProject_id(UUID project_id) {
		this.project_id = project_id;
	}

	public UUID getUser_id() {
		return user_id;
	}

	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}
    
    
    
    
    
}
