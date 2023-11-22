package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.BaiDang;
import com.entity.DichVu;
import com.service.BaiDangService;
import com.service.DichVuService;

@Controller
public class BaiVietController {

	@Autowired
	BaiDangService baidangService;
	@Autowired
	DichVuService dichvuService;

	@RequestMapping("/baiviet/list")
	public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "ngaydang") String sortBy, Model model) {
		Page<BaiDang> list = baidangService.findAll(page, size, sortBy);
		model.addAttribute("items", list);
		
		List<BaiDang> list5 = baidangService.findTop5LatestBaidang();
		model.addAttribute("item", list5);
		
		List<BaiDang> list2 = baidangService.findTop2LatestBaidang();
		model.addAttribute("item2", list2);
		
		BaiDang list1 = baidangService.findTop1LatestBaidang();
		model.addAttribute("item1", list1);
		return "baiviet/list";
	}

	@RequestMapping("/baiviet/ctbaiviet/{id}")
	public String ctbaiviet(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			Model model, @PathVariable("id") Integer id) {
		BaiDang item = baidangService.findById(id);
		model.addAttribute("item", item);
		Page<DichVu> list = dichvuService.findAll5(page, size);
		model.addAttribute("items", list);
		return "baiviet/ctbaiviet";
	}

}
