package com.controller;

import java.security.Principal;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.config.CustomUser;
import com.dao.AuthorityDAO;
//import com.config.CustomUser;
import com.dao.KhachHangDAO;
import com.entity.Authority;
import com.entity.Role;
import com.entity.KhachHang;
import com.service.KhachHangService;
import com.service.ParamService;
import com.service.SessionService;
import com.service.impl.MailerServiceImpl;

@Controller
public class TaiKhoanController {
	@Autowired
	private BCryptPasswordEncoder passEncode;
	
	Random random = new Random(1000);
	
	@Autowired
	KhachHangDAO accountDAO;
	@Autowired
	AuthorityDAO aDao;
	@Autowired
	SessionService session;
	@Autowired
	ParamService paramService;
	@Autowired
	ServletContext app;
	@Autowired
	KhachHangService khService;

	@RequestMapping("/taikhoan/login/from")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng nhập thông tin");
		return "taikhoan/login";
	}

	@RequestMapping("/taikhoan/login/success")
	public String login(Model model , KhachHang user) {
		model.addAttribute("message", "Bạn đã đăng nhập thành công!");
		return "taikhoan/login";
	}

	@RequestMapping("/taikhoan/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "taikhoan/login";
	}

	@RequestMapping("/taikhoan/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "taikhoan/login";
	}
	
	@RequestMapping("/taikhoan/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Không có quyền truy xuất!");
		return "taikhoan/login";
	}

	@CrossOrigin("*")
	@ResponseBody
	@RequestMapping("/rest/taikhoan/authentication")
	public Object getAuthentication(HttpSession session) {
		return session.getAttribute("authentication");
	}
	
	@RequestMapping("/taikhoan/register")
	public String register(Model model) {
		KhachHang item = new KhachHang();
		model.addAttribute("item", item);
		model.addAttribute("message", "Vui lòng điền các thông tin");
		return "taikhoan/register";
	}

	@PostMapping("/register/create")
	public String create(@ModelAttribute KhachHang item, Model model , HttpSession session) {
		KhachHang user = khService.create(item);
		if (user != null) {
			session.setAttribute("message" , "Bạn đã đăng kí thành công vui lòng đăng nhập!");
			return "taikhoan/login";
			
		} else {
			model.addAttribute("message","Đăng ký không thành công");
		}
		return "redirect:/taikhoan/register";
	}

	@RequestMapping("/taikhoan/doimatkhau")
	public String doimatkhau(Model model) {
		model.addAttribute("taikhoan", "");
		return "taikhoan/doimatkhau";
	}

	@PostMapping("/taikhoan/doimatkhau")
	public String update(Principal p, @RequestParam("matkhau") String matkhau,
			@RequestParam("matkhaumoi") String matkhaumoi, @RequestParam("confimmaukhaumoi") String confimmaukhaumoi,
			Model model) {
		String taikhoan = p.getName();

		KhachHang user = accountDAO.findByTaikhoan(taikhoan);

		boolean f = passEncode.matches(matkhau, user.getMatkhau());
		if (f) {
             if(matkhaumoi.equals(confimmaukhaumoi)){
            	  user.setMatkhau(passEncode.encode(matkhaumoi));
            	  accountDAO.save(user);
            	 model.addAttribute("message","Đổi mật khẩu thành công"); 
            	 return "taikhoan/doimatkhau";
             }else {
            	 model.addAttribute("message","Mật khẩu mới không trùng khớp");
            	 return "taikhoan/doimatkhau";
             }
		} else {
			model.addAttribute("message","Mật khẩu cũ sai");

		}

		return "redirect:/taikhoan/doimatkhau";
	}
	
	@Autowired
	MailerServiceImpl mailer;
	
	@RequestMapping("/taikhoan/quenmatkhau")
	public String quenmatkhau() {
		return "taikhoan/quenmatkhau";
	}

	@PostMapping("quenmatkhau")
	public String change(Model model , HttpSession session) {
		String email = paramService.getString("email", "");
		String taikhoan = paramService.getString("taikhoan", "");
		String subject = "Mã OTP để xác nhận thông tin đổi mật khẩu";
		String body = "Mã OTP của bạn là: "  ;
        int otp = random.nextInt(99999999);		
		try {
			KhachHang user = accountDAO.findByTaikhoan(taikhoan);
				if(!user.getEmail().equals(email)) {
					model.addAttribute("message", "Sai Email!");
				}else {
					session.setAttribute("myOtp", otp);
					session.setAttribute("email", email);
					mailer.send(email, subject, body+otp);
					return "/taikhoan/formOTP";
				}
		} catch (Exception e) {
			model.addAttribute("message", "Tài Khoản Không Tồn Tại!");
		}
		return "taikhoan/quenmatkhau";
	}
	
	@PostMapping("/OTP")
	public String formOPT(@RequestParam("otp") int otp, HttpSession session) {
		int myOtp = (int) session.getAttribute("myOtp");
		String email = (String) session.getAttribute("email");
		
		if(myOtp == otp) {
			KhachHang user = this.accountDAO.getbyEmail(email);
			if(user== null) {
				session.setAttribute("message", "Tài khoản không tồn tại với email này");
			}else {
				
			}
			return "taikhoan/doimatkhaumoi";
		}else {
			session.setAttribute("message", "Mã OTP không đúng");
			return "taikhoan/formOTP";
		}
	}
	
	@PostMapping("/change-password")
	public String changePass(@RequestParam("newpass") String newpass , @RequestParam("confimpass") String confimpass,HttpSession session){
		if(newpass.equals(confimpass)) {
			String email = (String) session.getAttribute("email");
			KhachHang user = this.accountDAO.getbyEmail(email);
			user.setMatkhau(this.passEncode.encode(newpass));
			
			
			Role role = new Role();
			role.setId("USER");

			// Thêm vai trò cho người dùng
			Authority authority = new Authority();
			authority.setRole(role);
	        authority.setTaikhoan(user);
	        
	        aDao.save(authority);
	        
			accountDAO.save(user);
			session.setAttribute("message", "Mật khẩu đã được cập nhập ! Vui lòng đăng nhập lại ");
			return "redirect:taikhoan/login/from";

		}else {
			session.setAttribute("message", "Mật khẩu xác nhận không đúng");
			return "taikhoan/doimatkhaumoi";
		}
	}
	
	@GetMapping("/taikhoan/chinhsuathongtin")
	public String updateTT(@AuthenticationPrincipal CustomUser loger , Model model,HttpServletRequest request) {
		String taikhoan = request.getRemoteUser();
		KhachHang user = khService.findByTaikhoan(taikhoan);
		model.addAttribute("user" , user);
		return "taikhoan/chinhsuathongtin";
	}
	
	@PostMapping("/chinhsuaTT")
	public String updateUser(@AuthenticationPrincipal CustomUser loger ,@RequestParam("taikhoan") String taikhoan,@RequestParam("ten") String ten,@RequestParam("sdt") String sdt ,@RequestParam("email") String email,HttpSession session) {
	      KhachHang user = this.accountDAO.findByTaikhoan(taikhoan);
	      user.setEmail(email);
	      user.setSdt(sdt);
	      user.setTen(ten);
	      accountDAO.save(user);
	      session.setAttribute("messsage", "Thông tin đã được cập nhập ");
		return "redirect:taikhoan/chinhsuathongtin";
	
	}
	
}
