package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AuthorityDAO;
import com.dao.KhachHangDAO;
import com.entity.Authority;
import com.entity.KhachHang;
import com.service.AuthorityService;



@Service
public class AuthorityServiceImpl implements AuthorityService{
	
	@Autowired private AuthorityDAO authdao;
	@Autowired private KhachHangDAO accdao;
	
	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<KhachHang> accounts = accdao.getAdministrators();
		return authdao.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		return authdao.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		return authdao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		authdao.deleteById(id);
	}

	@Override
	public List<Authority> getOneByRole(String taikhoan) {
		return authdao.getOneByRole(taikhoan);
	}


	/*Summary*/
	@Override
	public Long getTotalCustomer() {
		return authdao.findAll().stream().filter(e->e.getRole().getName().equals("Customers")).count();
	}

	@Override
	public void deleteByUserName(String taikhoan) {
		authdao.deleteByUserName(taikhoan);
		
	}

	
}
