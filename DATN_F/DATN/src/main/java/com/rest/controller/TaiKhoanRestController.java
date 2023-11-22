package com.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.SanPham;
import com.entity.KhachHang;
import com.service.KhachHangService;



@CrossOrigin("*")
@RestController
@RequestMapping("rest")
public class TaiKhoanRestController {
	@Autowired
	private KhachHangService accountService;

	@GetMapping("accounts")
	public List<KhachHang> getAccounts(@RequestParam("admin") Optional<Boolean> admin) {
		if (admin.orElse(false)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}
	@GetMapping("{taikhoan}")
	public KhachHang getOne(@PathVariable("taikhoan") String  taikhoan) {
		return accountService.findByTaikhoan(taikhoan);
	}

	@PostMapping("accountsManage")
	public KhachHang create(@RequestBody KhachHang account) {
		return accountService.create(account);
	}

	@PutMapping("accounts/{id}")
	public KhachHang update(@RequestBody KhachHang account, @PathVariable("id") String taikhoan) {
		return accountService.update(account);
	}
	
	@DeleteMapping("accounts/{taikhoan}")
	public void delete(@PathVariable("taikhoan") String taikhoan) {
		 accountService.deletebyId(taikhoan);
	}
}
