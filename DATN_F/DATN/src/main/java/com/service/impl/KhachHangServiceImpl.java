package com.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dao.AuthorityDAO;
import com.dao.RoleDAO;
import com.dao.KhachHangDAO;
import com.entity.Authority;
import com.entity.Role;
import com.entity.KhachHang;
import com.service.KhachHangService;

@Service
public class KhachHangServiceImpl implements KhachHangService {
	@Autowired
	KhachHangDAO dao;

	@Autowired
	AuthorityDAO aDao;

	@Autowired
	RoleDAO rDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncode;

	public List<KhachHang> findAll() {
		return dao.findAll();
	}

	public KhachHang create(KhachHang user) {
		user.setMatkhau(passwordEncode.encode(user.getMatkhau()));
		dao.save(user);
		Role role = new Role();
		role.setId("USER");

		// Thêm vai trò cho người dùng
		Authority authority = new Authority();
		authority.setRole(role);
        authority.setTaikhoan(user);
        
        aDao.save(authority);
        
        return user;
	}

	@Override
	public KhachHang findByTaikhoan(String taikhoan) {
		return dao.findByTaikhoan(taikhoan);
	}

	@Override
	public KhachHang update(KhachHang user) {
		user.setMatkhau(passwordEncode.encode(user.getMatkhau()));
		return user;
	}

//	@Override
//	public TaiKhoan updateUser(String email, String ten, String sdt, int id) {
//		return dao.updateUser(email, ten, sdt, id);
//	}

	@Override
	public List<KhachHang> getAdministrators() {
		return dao.getAdministrators();
	}

	@Override
	public void deletebyId(String taikhoan) {
		dao.deleteById(taikhoan);

	}

	@Override
	public KhachHang updateUser(String email, String ten, String sdt, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByTaikhoan(String taikhoan) {
		dao.deleteByTaikhoan(taikhoan);
		
	}

	@Override
	public List<Object[]> top10Customer() {
		
		return dao.top10Customer();
	}

	@Override
	public Long getTotalAccount() {
		return dao.count();
	}

}
