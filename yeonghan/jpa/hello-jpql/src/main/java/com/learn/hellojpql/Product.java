package com.learn.hellojpql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int price;
	private int stock;
}
