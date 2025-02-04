package com.example.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer_address")
public class Address {
	
	@Id
	private Long id;
	
	@Column(nullable = false, length = 6)
	private Integer postalCode;
	
	@Column(nullable = false, length = 50)
	private String streetAndNumber;

	@Column(length = 5)
	private String door;
	
	@Column(nullable = false, length = 3)
	private String country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPostalCode() {
	    return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
	    this.postalCode = postalCode;
	}

	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", postalCode=" + postalCode + ", streetAndNumber=" + streetAndNumber + ", door="
				+ door + ", country=" + country + "]";
	}
	
	

}
