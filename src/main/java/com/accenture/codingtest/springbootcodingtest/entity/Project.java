package com.accenture.codingtest.springbootcodingtest.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "Project")
public class Project {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private UUID id;

    @NotBlank(message = "Required Field")
    @Column(name = "name", unique = true)
    private String name;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }  
}