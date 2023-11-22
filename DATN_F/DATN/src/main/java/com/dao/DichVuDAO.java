package com.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.DichVu;

public interface DichVuDAO extends JpaRepository<DichVu, Integer>{
	
	@Query(value = "SELECT GIA FROM DICHVU WHERE id =?1", nativeQuery = true)
	DichVu findByGiaToID(Integer id);
	
	@Query(value = "SELECT id, ten, hinhanh1, FORMAT(CONVERT(DECIMAL(10,2), gia), '#,##0') as gia, noidung, hinhanh2, luuy, lienhe, href, taikhoan\r\n"
			+ "FROM DICHVU WHERE id =?1", nativeQuery = true)
	DichVu finfByIdFormat(Integer id);
	
//	@Query(value = "SELECT GIA FROM DICHVU WHERE id =?1", nativeQuery = true)
//	Page<DichVu> findA2(int page, int size);
}
