package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="sanpham")
public class SanPham implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String tensp;
	String mota;
	String noidung;
	Integer gia;
	String hinh;
	String masp;
	
	@ManyToOne
	@JoinColumn(name = "idloai")
	Loai loai;
	
	@ManyToOne
	@JoinColumn(name = "taikhoan")
	KhachHang taikhoan;
	
	@JsonIgnore
	@OneToMany(mappedBy = "sanpham")
	List<ChiTietDonHang> chitietdonhangs;	
	
	@JsonIgnore
	@OneToMany(mappedBy = "sanpham")
	List<BinhLuan> binhluan;	
	
}
