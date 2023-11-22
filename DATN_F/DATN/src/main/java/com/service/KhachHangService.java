package com.service;

import java.util.List;

import com.entity.KhachHang;

public interface KhachHangService {
	public List<KhachHang> findAll();

	public KhachHang  findByTaikhoan(String taikhoan);
	
	public KhachHang create(KhachHang user);
	
	public KhachHang update(KhachHang user);
	
    public KhachHang updateUser(String email,String ten , String sdt , int id);
    
    List<KhachHang> getAdministrators();
    
    void  deletebyId(String taikhoan);
    
    void deleteByTaikhoan(String taikhoan);

    List<Object[]> top10Customer();
    
    Long getTotalAccount();
}
