package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.KhachHangDAO;
import com.entity.KhachHang;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	 KhachHangDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String taikhoan) throws UsernameNotFoundException {
		KhachHang user = dao.findByTaikhoan(taikhoan);
		if(user != null) {
			return new CustomUser(user);
		}
		throw new UsernameNotFoundException("User không tồn tại!");
	}
	
	

}
