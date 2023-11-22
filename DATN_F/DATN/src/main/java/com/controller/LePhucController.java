package com.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.SanPham;
import com.service.SanPhamService;
import com.dao.SanPhamDAO;
import com.entity.BinhLuan;
import com.entity.Loai;
import com.entity.KhachHang;

import com.dao.BinhLuanDAO;
import com.dao.KhachHangDAO;
import com.dao.LoaiDAO;

@Controller
public class LePhucController {
	@Autowired
	KhachHangDAO khdao;
	@Autowired
	BinhLuanDAO bldao;
	@Autowired
	SanPhamDAO spdao;
	@Autowired
	SanPhamService sanphamservice;
	@Autowired
	LoaiDAO ldao;
	@RequestMapping("/lephuc/list")
		public String lephuc(Model model, @RequestParam("cid") Integer cid) {
			if(cid!=0) {
				List<SanPham> sps =spdao.findByIdsp(cid);
				model.addAttribute("sps", sps);
			}else {
		List<SanPham> sps = spdao.findAll();
		model.addAttribute("sps", sps);
			}
		List<Loai> l = ldao.findAll();
		model.addAttribute("l", l);
		return "lephuc/list";
	}

	@RequestMapping("/lephuc/ctlephuc/{id}")
	public String ctlephuc(Model model, @PathVariable("id") Integer id) {
		SanPham sp = sanphamservice.findById(id);
		model.addAttribute("sp",sp);
		List<BinhLuan> bl = bldao.findByIdbl(id);
		model.addAttribute("bl",bl);
		Loai clt = sp.getLoai();
		List<SanPham> cl = spdao.findByIdsp(clt.getId());
		model.addAttribute("cl",cl);
		return "lephuc/ctlephuc";
	}
	@PostMapping("/lephuc/binhluan/{id}")
	public String create(@PathVariable("id") Integer id,BinhLuan cmt , HttpServletRequest request , HttpSession session) {
		String taikhoan = request.getRemoteUser();
		KhachHang test= cmt.getTaikhoan();
		if(taikhoan == null) {
			session.setAttribute("message", "Bạn phải đăng nhập để bình luận");
			
		}else {
			test = khdao.findByTaikhoan(taikhoan);
			SanPham sp =spdao.findById(id).get(); 
			cmt.setTaikhoan(test);
			cmt.setSanpham(sp);
			bldao.save(cmt);
		}
		
		return "redirect:/lephuc/ctlephuc/{id}";
	}

}