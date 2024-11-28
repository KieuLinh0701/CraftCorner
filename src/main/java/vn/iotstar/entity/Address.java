package vn.iotstar.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "addresses")
@NamedQuery(name = "Address.findAll", query = "SELECT a FROM Address a")
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private int address_id;

	@Column(name = "city", columnDefinition = "NVARCHAR(255) NULL")
	private String city;
	
	@Column(name = "district", columnDefinition = "NVARCHAR(255) NULL")
	private String district;
	
	@Column(name = "ward", columnDefinition = "NVARCHAR(255) NULL")
	private String ward;
	
	@Column(name = "detail", columnDefinition = "NVARCHAR(255) NULL")
	private String detail;

	public Address() {
	}

	public Address(String city, String district, String ward, String detail) {
		this.city = city;
		this.district = district;
		this.ward = ward;
		this.detail = detail;
	}

	public Address(int address_id, String city, String district, String ward, String detail) {
		this.address_id = address_id;
		this.city = city;
		this.district = district;
		this.ward = ward;
		this.detail = detail;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
