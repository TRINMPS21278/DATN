package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.entity.BaiDang;


public interface BaiDangDAO extends JpaRepository<BaiDang, Integer>{
		
	 @Query(value = "SELECT Top 2 * FROM Baidang ORDER BY ngaydang DESC",nativeQuery = true)
	    List<BaiDang> findTop2LatestBaidang();
	 @Query(value = "SELECT Top 5 * FROM Baidang ORDER BY ngaydang DESC",nativeQuery = true)
	    List<BaiDang> findTop5LatestBaidang();
	 @Query(value = "SELECT Top 1 * FROM Baidang ORDER BY ngaydang DESC",nativeQuery = true)
	    BaiDang findTop1LatestBaidang();
}
 