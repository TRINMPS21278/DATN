package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.BinhLuan;

public interface BinhLuanDAO extends JpaRepository<BinhLuan, Integer>{
//	@Query("SELECT p FROM BINHLUAN p WHERE p.idsanpham=?1")
//	List<BinhLuan> findbl(String a);
	@Query(value="SELECT * FROM BINHLUAN WHERE idsanpham=?1",nativeQuery = true)
	List<BinhLuan> findByIdbl(Integer id);
}
