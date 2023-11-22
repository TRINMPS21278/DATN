package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.Authority;
import com.entity.KhachHang;


@Repository
public interface AuthorityDAO extends JpaRepository<Authority, Integer>{

	@Query("Select Distinct a From Authority a where a.taikhoan IN ?1")
	List<Authority> authoritiesOf(List<KhachHang> taikhoan);

	@Query("Select a From Authority a where a.taikhoan.taikhoan like ?1")
	List<Authority> getOneByRole(String taikhoan);
 

	@Query("Delete from Authority where taikhoan = ?1")
	void deleteByUserName(String taikhoan);

}
