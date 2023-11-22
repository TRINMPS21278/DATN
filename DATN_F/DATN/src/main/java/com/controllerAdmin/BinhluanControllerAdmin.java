package com.controllerAdmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dao.BinhLuanDAO;
import com.dao.KhachHangDAO;
import com.dao.SanPhamDAO;
import com.entity.BinhLuan;
import com.entity.KhachHang;
import com.entity.Loai;
import com.entity.SanPham;
import com.service.BinhLuanService;

@Controller
public class BinhluanControllerAdmin {
	@Autowired
	SanPhamDAO spdao;
	@Autowired
	KhachHangDAO dao;
	@Autowired
	BinhLuanService bl;
	@RequestMapping("/admin/binhluan/editBinhLuan")
	public String editBinhluan(Model model) {
		List<BinhLuan> b = bl.findAll();
		model.addAttribute("b",b);
		return "admin/binhluan/editBinhluan";
	}
	@RequestMapping("/binhluan/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		bl.delete(id);
		return "redirect:/admin/binhluan/editBinhLuan";
	}
//	@RequestMapping("/binhluan/create")
//	public String create(@PathVariable("id") Integer id , BinhLuan loai , HttpServletRequest request) {
//		String taikhoan = request.getRemoteUser();
//		
//		KhachHang test= loai.getTaikhoan();
//		test = dao.findByTaikhoan(taikhoan);
//		SanPham sp =spdao.findById(id).get(); 
//		loai.setTaikhoan(test);
//		loai.setSanpham(sp);
//		bl.create(loai);
//		return "redirect:/admin/binhluan/editBinhLuan";
//	}
}
