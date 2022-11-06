package com.ing.applebag.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Bag {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	private int numOfApples;
	private String supplier;
	@CreationTimestamp
	private LocalDateTime dateTime;
	private float price;
	
	
	public Bag() {
		super();
	}

	public Bag(int numOfApples, String supplier, float price) {
		this.numOfApples = numOfApples;
		this.supplier = supplier;
		this.price = price;
	}
	
	
	
	public Bag(String id, int numOfApples, String supplier, LocalDateTime dateTime, float price) {
		this.id = id;
		this.numOfApples = numOfApples;
		this.supplier = supplier;
		this.dateTime = dateTime;
		this.price = price;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public int getNumOfApples() {
		return numOfApples;
	}

	public void setNumOfApples(int numOfApples) {
		this.numOfApples = numOfApples;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
}
