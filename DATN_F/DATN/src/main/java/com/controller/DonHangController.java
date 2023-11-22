package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.DonHang;
import com.service.DonHangService;
import com.service.KhachHangService;

@Controller
public class DonHangController {
	@Autowired
	DonHangService orderService;
	
	@Autowired
	KhachHangService khachHangService;
	
	@RequestMapping("/donhang/checkout")
	public String checkout(Model model, HttpServletRequest request) {
		String username=request.getRemoteUser();
		String taikhoan=username;
		model.addAttribute("user",khachHangService.findByTaikhoan(taikhoan));
		return "donhang/checkout";
	}
	
	@RequestMapping("/donhang/list")
	public String list(Model model, HttpServletRequest request) {
		String username=request.getRemoteUser();
		String taikhoan=username;
		model.addAttribute("donhang",orderService.findByIdKhachHang(taikhoan));
		return "donhang/list";
	}	
	
	@RequestMapping("/donhang/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("donhang", orderService.findById(id));
		return "donhang/detail";
	}
	
	@RequestMapping("/donhang/huy")
	public String update(Model model, @ModelAttribute("donhang") DonHang donHang) {
		donHang.setTrangthai(5);
		orderService.update(donHang);
		return "redirect:/donhang/list";
	}
}
