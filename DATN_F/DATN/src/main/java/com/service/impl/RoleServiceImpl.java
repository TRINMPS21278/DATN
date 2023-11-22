package com.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RoleDAO;
import com.entity.Role;
import com.service.RoleService;



@Service
public class RoleServiceImpl implements RoleService{

	@Autowired private RoleDAO dao;

	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}
	
}
