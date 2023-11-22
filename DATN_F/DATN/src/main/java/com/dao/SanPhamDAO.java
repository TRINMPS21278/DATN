package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.entity.BinhLuan;
import com.entity.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, Integer>{
	@Query(value="SELECT * FROM sanpham WHERE idloai=?1",nativeQuery = true)
	List<SanPham> findByIdsp(Integer id);
	
	/*Summary*/
//	@Query("Select Count(p) from Product p where p.available = true")
//	Long getAvailable();

	@Query(value="Select c.ten, ISNULL(sum(odt.soluong),0) from LOAI c  "
			+ "inner join SANPHAM p on c.id = p.idloai "
			+ "inner join CHITIETDONHANG odt on p.id = odt.idsanpham "
			+ "inner join DONHANG o on odt.iddonhang = o.id "
			+ "Where cast(o.ngaytao as date) >= DateAdd(day,-365,cast(getdate() as date)) "
			+ "group by c.ten",nativeQuery = true)
	List<Object[]> numberOfProductSoldByType();

	@Query(value="Select c.ten, ISNULL(sum(odt.soluong),0) from LOAI c  "
			+ "inner join SANPHAM p on c.id = p.idloai "
			+ "inner join CHITIETDONHANG odt on p.id = odt.idsanpham "
			+ "inner join DONHANG o on odt.iddonhang = o.Id "
			+ "group by c.ten",nativeQuery = true)
	List<Object[]> getPercentByCate();
//
//	@Query(value = "with table1 as ( "
//			+ "	Select c.name as catename,  "
//			+ "	count(p.Available) as available "
//			+ "	from Categories c "
//			+ "	inner join Products p on c.Id = p.CategoryId "
//			+ "	where p.Available = 1 "
//			+ "	group by c.Name "
//			+ "), "
//			+ "table2 as ( "
//			+ "	Select c.name as catename,  "
//			+ "	count(p.Available) as unavailable "
//			+ "	from Categories c "
//			+ "	inner join Products p on c.Id = p.CategoryId "
//			+ "	where p.Available = 0 "
//			+ "	group by c.Name "
//			+ ") "
//			+ "Select t1.catename,t1.available,t2.unavailable  "
//			+ "from table1 t1 inner join table2 t2 on t1.catename = t2.catename", nativeQuery = true)
//	List<Object[]> availableRate();

	@Query(value="Select top 10 p.tensp, count(odt.idsanpham) as mostSold "
			+ "From CHITIETDONHANG odt inner join SANPHAM p "
			+ "on odt.idsanpham = p.id "
			+ "group by p.tensp "
			+ "Order by mostSold desc",nativeQuery = true)
	List<Object[]> top10Product();
}
