package com.service;

import java.util.List;

import com.entity.BinhLuan;

public interface BinhLuanService {
public List<BinhLuan> findAll() ;
	
	public BinhLuan findById(Integer id) ;

//	public List<SanPham> findByCategoryId(String cid) ;

	public BinhLuan create(BinhLuan bl) ;

	public BinhLuan update(BinhLuan bl) ;

	public void delete(Integer id) ;
}
