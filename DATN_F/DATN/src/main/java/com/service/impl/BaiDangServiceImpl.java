package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dao.BaiDangDAO;
import com.entity.BaiDang;

import com.service.BaiDangService;

@Service
public class BaiDangServiceImpl implements BaiDangService {
	@Autowired
	BaiDangDAO dao;

	@Override
	public Page<BaiDang> findAll(int page, int size, String sortBy) {
		Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortBy).descending());
		return dao.findAll(pageable);
	}

	@Override
	public BaiDang findById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public List<BaiDang> findTop5LatestBaidang() {
		return dao.findTop5LatestBaidang();
	}

	@Override
	public List<BaiDang> findTop2LatestBaidang() {
		return dao.findTop2LatestBaidang();
	}

	@Override
	public BaiDang findTop1LatestBaidang() {
		return dao.findTop1LatestBaidang();
	}

	@Override
	public Page<BaiDang> findAll(int page, int size) {
		Pageable pageable = PageRequest.of(page - 1, size);
		return dao.findAll(pageable);
	}

	@Override
	public BaiDang save(BaiDang baiDang) {
		return dao.save(baiDang);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}


}
