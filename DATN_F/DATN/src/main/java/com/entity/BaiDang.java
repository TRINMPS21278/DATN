package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="baidang")
public class BaiDang implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String tieude;
	String noidung;
	String hinhanh;
	@Temporal(TemporalType.DATE)
	@Column(name = "ngaydang")
	Date ngaydang = new Date();
	
	@ManyToOne
	@JoinColumn(name = "taikhoan")
	KhachHang taikhoan;
	
	
}
