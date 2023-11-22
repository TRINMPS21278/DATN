package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BinhLuanDAO;
import com.entity.BinhLuan;
import com.service.BinhLuanService;

@Service
public class BinhLuanServiceImpl implements BinhLuanService{
	@Autowired
	BinhLuanDAO dao;
	public List<BinhLuan> findAll() {
		return dao.findAll();
	}
	
	public BinhLuan findById(Integer id) {
		return dao.findById(id).get();
	}

//	public List<SanPham> findByCategoryId(String cid) {
//		return dao.findByCategoryId(cid);
//	}

	public BinhLuan create(BinhLuan bl) {
		return dao.save(bl);
	}

	public BinhLuan update(BinhLuan bl) {
		return dao.save(bl);
	}

	public void delete(Integer id) {
		dao.deleteById(id);
	}
	

}
