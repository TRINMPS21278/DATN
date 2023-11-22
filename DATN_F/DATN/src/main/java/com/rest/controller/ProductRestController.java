package com.rest.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.KhachHang;
import com.entity.SanPham;
import com.service.SanPhamService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	SanPhamService productService;
	
	@GetMapping("{id}")
	public SanPham getOne(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}
	
	@GetMapping()
	public List<SanPham> getAll() {
		return productService.findAll();
	}
	
	@PostMapping
	public SanPham create(@RequestBody SanPham product , HttpServletRequest request) {
		KhachHang ad = new KhachHang();
		String taikhoan = request.getRemoteUser();
		ad.setTaikhoan(taikhoan);
		product.setTaikhoan(ad);
		return productService.create(product);
	}
	
	@PutMapping("{id}")
	public SanPham update(@PathVariable("id") Integer id, @RequestBody SanPham product) {
		return productService.update(product);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		productService.delete(id);
	}
}
