package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.Authority;

@Service
public interface AuthorityService {
	List<Authority> findAuthoritiesOfAdministrators();

	List<Authority> findAll();

	Authority create(Authority auth);

	void delete(Integer id);

	List<Authority> getOneByRole(String taikhoan);

	void deleteByUserName(String taikhoan);

	Long getTotalCustomer();
	
}
