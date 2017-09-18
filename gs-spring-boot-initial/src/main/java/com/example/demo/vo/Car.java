/**
 * 
 */
package com.example.demo.vo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import static javax.persistence.GenerationType.IDENTITY;
/**
 * @author SR4PXM
 *
 */

public class Car {
    
	private Long id;
	private String licensePlate;
	private String manufacturer;
	private String manufactureDate;
	/**
	 * 
	 */
	public Car() {
	}

	public Car(String license, String manufacturer1, String manufactureDate1) {
		licensePlate = license;
		manufacturer = manufacturer1;
		manufactureDate = manufactureDate1;
	}

	private int age;
	private int version;
	
	/**
	 * @return the id
	 */

	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the licensePlate
	 */
	public String getLicensePlate() {
		return licensePlate;
	}
	/**
	 * @param licensePlate the licensePlate to set
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	/**
	 * @return the manufactureDate
	 */
	public String getManufactureDate() {
		return manufactureDate;
	}
	/**
	 * @param manufactureDate the manufactureDate to set
	 */
	public void setManufactureDate(String manufactureDate) {
		this.manufactureDate = manufactureDate;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	public String toString() {
		return "License: " + licensePlate + " - Manufacturer: " + manufacturer
		+ " - Manufacture Date: " + manufactureDate + " - Age: " + age;
		}
	
}
