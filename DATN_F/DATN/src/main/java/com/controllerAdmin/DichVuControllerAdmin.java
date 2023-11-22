package com.controllerAdmin;

import java.util.List;

import javax.servlet.ServletContext;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.DichVu;
import com.service.DichVuService;

@Controller
public class DichVuControllerAdmin {

	@Autowired
	DichVuService dichvuService;

	@RequestMapping("/admin/dichvu/list")
	public String listdichvu(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "6") int size,
			Model model) {
		Page<DichVu> list = dichvuService.findAll(page, size);
		model.addAttribute("items", list);
		return "admin/dichvu/listdichvu";
	}

	@GetMapping("/admin/dichvu/add")
	public String addForm(Model model) {
		model.addAttribute("dichVu", new DichVu());
		return "admin/dichvu/editdichvu";
	}

	@PostMapping("/admin/dichvu/add")
	public String addDichVu(@ModelAttribute DichVu dichVu, @RequestParam("hinhanh1File") MultipartFile hinhanh1File,
			@RequestParam("hinhanh2File") MultipartFile hinhanh2File) {
		DichVu savedDichVu = dichvuService.save(dichVu);

		saveImage(hinhanh1File, "hinhanh1", savedDichVu.getId());
		saveImage(hinhanh2File, "hinhanh2", savedDichVu.getId());
		
		return "redirect:/dichvu/list";
	}

	@GetMapping("/admin/dichvu/edit/{id}")
	public String editForm(@PathVariable Integer id, Model model) {
		DichVu dichVu = dichvuService.findById(id);
		model.addAttribute("dichVu", dichVu);
		return "admin/dichvu/editdichvu";
	}

	@PostMapping("/admin/dichvu/edit")
	public String editDichVu(@ModelAttribute DichVu dichVu, @RequestParam("hinhanh1File") MultipartFile hinhanh1File,
			@RequestParam("hinhanh2File") MultipartFile hinhanh2File) {
		String hinhanh1FileName = hinhanh1File.getOriginalFilename();
		String hinhanh2FileName = hinhanh2File.getOriginalFilename();
		dichVu.setHinhanh1(hinhanh1FileName);
		dichVu.setHinhanh2(hinhanh2FileName);

		DichVu savedDichVu = dichvuService.save(dichVu);

		saveImage(hinhanh1File, "hinhanh1", savedDichVu.getId());
		saveImage(hinhanh2File, "hinhanh2", savedDichVu.getId());
		
		
		return "redirect:/dichvu/list";
	}

	@GetMapping("/admin/dichvu/delete/{id}")
	public String deleteDichVu(@PathVariable Integer id) {
		dichvuService.delete(id);
		return "redirect:/admin/dichvu/list";
	}

	private void saveImage(MultipartFile file, String prefix, Integer dichVuId) {
		if (file != null && !file.isEmpty()) {
			try {
				String fileName = file.getOriginalFilename();
				File targetFile = new File("src/main/resources/static/assets/images/" + fileName);
				FileCopyUtils.copy(file.getBytes(), targetFile);
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
	}
}