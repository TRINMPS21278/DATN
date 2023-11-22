package com.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Authority;
import com.service.AuthorityService;


@CrossOrigin("*")
@RestController
@RequestMapping("rest")
public class AuthorityRestController {
@Autowired private AuthorityService authService;
	
	@GetMapping("authorities")
	public List<Authority> findAll(@RequestParam("admin")Optional <Boolean> admin){
		if(admin.orElse(false)) {
			return authService.findAuthoritiesOfAdministrators();
		}
		return authService.findAll();
	}
	
	@GetMapping("authoritiesOne")
	public List<Authority> getOneByRole(@RequestParam("taikhoan")String taikhoan){
		return authService.getOneByRole(taikhoan);
	}
	
	
	@PostMapping("authorities")
	public Authority post(@RequestBody Authority auth) {
		return authService.create(auth);
	}
	
	@DeleteMapping("authorities/{id}")
	public void delete(@PathVariable("id") Integer id) {
		authService.delete(id);
	}
	
	
	@DeleteMapping("authoritiesOne/{taikhoan}")
	public void deleteByUsername(@PathVariable("taikhoan") String taikhoan) {
		System.out.println("username del: "+taikhoan);
		authService.deleteByUserName(taikhoan);
	}
}
