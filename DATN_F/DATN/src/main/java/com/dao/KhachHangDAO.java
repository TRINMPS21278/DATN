package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.entity.KhachHang;



public interface KhachHangDAO extends JpaRepository<KhachHang, String> {

	public KhachHang findByTaikhoan(String taikhoan);

	@Query("select u from KhachHang u where u.email=:email")
	public KhachHang getbyEmail(@Param("email") String email);

	
	@Query("Select Distinct ar.taikhoan From Authority ar where ar.role.id IN ('ADMIN')")
	List<KhachHang> getAdministrators();
	
	@Query(value="DELETE from KhachHang  join DonHang d on TaiKhoan.taikhoan = DonHang.taikhoan where TaiKhoan.taikhoan = :taikhoan",nativeQuery = true)
	void deleteByTaikhoan(String taikhoan);
	
	@Query(value="Select a.ten, a.email, a.sdt, "
			+ "sum(odt.gia * odt.soluong) as totalPayment "
			+ "From KHACHHANG a inner join DONHANG o on a.taikhoan = o.taikhoan "
			+ "inner join CHITIETDONHANG odt on o.id = odt.iddonhang "
			+ "Group by a.ten, a.email, a.sdt "
			+ "order by totalPayment desc",nativeQuery = true)
	List<Object[]> top10Customer();
}
