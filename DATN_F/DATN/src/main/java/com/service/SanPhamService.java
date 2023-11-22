package com.service;

import java.util.List;

import com.entity.SanPham;



public interface SanPhamService {
public List<SanPham> findAll() ;
	
	public SanPham findById(Integer id) ;

//	public List<SanPham> findByCategoryId(String cid) ;

	public SanPham create(SanPham sanpham) ;

	public SanPham update(SanPham sanpham) ;

	public void delete(Integer id) ;
	
	List<Object[]> numberOfProductSoldByType();
	List<Object[]> getPercentByCate();
	List<Object[]> top10Product();
	Long getTotalProduct();
}
