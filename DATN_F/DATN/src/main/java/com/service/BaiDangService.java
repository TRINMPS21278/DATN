package com.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.entity.BaiDang;


public interface BaiDangService {
	public Page<BaiDang> findAll(int page, int size, String sortBy);
    public BaiDang findById(Integer id);
    public List<BaiDang> findTop5LatestBaidang();
    public List<BaiDang> findTop2LatestBaidang();
    public BaiDang findTop1LatestBaidang();
    public Page<BaiDang> findAll(int page, int size);
    BaiDang save(BaiDang baiDang);
    void delete(Integer id);
}