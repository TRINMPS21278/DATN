package com.controllerAdmin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Loai;

import com.service.LoaiService;



@Controller
public class LoaiControllerAdmin {
	@Autowired
	LoaiService loai;


	@RequestMapping("/admin/binhluan/editLoai")
	public String editloai(Model model) {
		Loai l1 = new Loai();
		model.addAttribute("l1",l1);
		List<Loai> l = loai.findAll();
		model.addAttribute("l",l);
		
		return "admin/binhluan/editLoai";
	}
	@RequestMapping("/loai/edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Loai l1 = loai.findById(id);
		model.addAttribute("l1", l1);
		List<Loai> l = loai.findAll();
		model.addAttribute("l",l);
		return"admin/binhluan/editLoai";
	}
	
	@RequestMapping("/loai/create")
	public String create(Loai loai) {
		this.loai.create(loai);
		return "redirect:/admin/binhluan/editLoai";
	}
	
	@RequestMapping("/loai/update/{id}")
	public String update(@PathVariable("id") Integer id, Loai loai) {
		this.loai.update(loai);
		return "redirect:/admin/binhluan/editLoai";
	}
	
	@RequestMapping("/loai/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		loai.delete(id);
		return "redirect:/admin/binhluan/editLoai";
	}
}