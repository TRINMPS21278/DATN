package com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@SuppressWarnings("serial")
@Data
@Entity
@Table(name="taikhoan")
public class TaiKhoan implements Serializable{
	@Id
	String tentaikhoan;
	String matkhau;
	String ten;
	String sdt;
	String email;
	String diachi;
	String hinhanh;
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<DonHang> donhangs;

	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<GioHang> giohangs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<BinhLuanLePhuc> binhluanklephucs;
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<BinhLuanBaiViet> binhluanbaiviets;

	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan", fetch = FetchType.EAGER)
	List<Quyen> quyens;
	
}
