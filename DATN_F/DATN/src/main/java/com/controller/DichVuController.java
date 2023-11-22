package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.DichVu;
import com.service.DichVuService;

@Controller
public class DichVuController {

	@Autowired
	DichVuService dichvuService;

	@RequestMapping("/dichvu/list")
	public String dichvu(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "6") int size,
			Model model) {
		Page<DichVu> list = dichvuService.findAll(page, size);
		model.addAttribute("items", list);
		return "dichvu/list";
	}

	@RequestMapping("/dichvu/ctdichvu/{id}")
	public String ctdichvu(Model model, @PathVariable("id") Integer id) {
		DichVu item = dichvuService.findByIdFormat(id);
		model.addAttribute("item", item);
		return "dichvu/ctdichvu";
	}

}
