package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@SuppressWarnings("serial")
@Data
@Entity
@Table(name="khachhang")
public class KhachHang implements Serializable{
	@Id
	String taikhoan;
	String matkhau;
	String ten;
	String gioitinh;
	String sdt;
	String email;
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<DonHang> donhang;
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<BinhLuan> binhluan;

	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan", fetch = FetchType.EAGER)
	List<Authority> authorities;
	
}
