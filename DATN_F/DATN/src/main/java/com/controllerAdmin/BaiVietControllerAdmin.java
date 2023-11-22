package com.controllerAdmin;

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

import com.entity.BaiDang;
import com.entity.DichVu;
import com.service.BaiDangService;


@Controller
public class BaiVietControllerAdmin {
	
	@Autowired
	BaiDangService baidangService;

	@RequestMapping("/admin/baiviet/list")
	public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "ngaydang") String sortBy, Model model) {
		Page<BaiDang> list = baidangService.findAll(page, size, sortBy);
		model.addAttribute("items", list);
		return "admin/baiviet/listbaiviet";
	}

	@RequestMapping("/admin/baiviet/edit")
	public String edit() {
		return "admin/baiviet/editbaiviet";
	}
	
	@GetMapping("/admin/baiviet/add")
	public String addForm(Model model) {
		model.addAttribute("baiViet", new BaiDang());
		return "admin/baiviet/editbaiviet";
	}

	@PostMapping("/admin/baiviet/add")
	public String addDichVu(@ModelAttribute BaiDang baiViet) {
		baidangService.save(baiViet);
		return "redirect:/baiviet/list";
	}

	@GetMapping("/admin/baiviet/edit/{id}")
	public String editForm(@PathVariable Integer id, Model model) {
		BaiDang baiViet = baidangService.findById(id);
		model.addAttribute("baiViet", baiViet);
		return "admin/baiviet/editbaiviet";
	}

	@PostMapping("/admin/baiviet/edit")
	public String editDichVu(@ModelAttribute BaiDang baiViet) {
		baidangService.save(baiViet);
		return "redirect:/baiviet/list";
	}

	@GetMapping("/admin/baiviet/delete/{id}")
	public String deleteDichVu(@PathVariable Integer id) {
		baidangService.delete(id);
		return "redirect:/admin/baiviet/list";
	}
}