package com.stockpin.project.entity;

import jakarta.persistence.Entity;

@Entity
public class User {

	private long id;
	private String pw;
	private String name;
	private String fullName;
	private String phone;
}
