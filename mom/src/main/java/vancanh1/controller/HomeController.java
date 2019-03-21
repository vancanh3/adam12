package vancanh1.controller;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vancanh1.entity.*;
import vancanh1.model.Employee;
import vancanh1.model.EmployeeJsonRespone;
import vancanh1.model.UserJsonRespone;
import vancanh1.repository.GiaoVienRepository;
import vancanh1.repository.NguoiDungRepository;
import vancanh1.repository.SinhVienRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    @Autowired
    NguoiDungRepository nguoiDungRepository;

    @Autowired
    SinhVienRepository sinhVienRepository;

    @Autowired
    GiaoVienRepository giaoVienRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String showLoginPage(Model model) {
        return "login";
    }
    @PostMapping(value = "/saveEmployee", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public UserJsonRespone saveEmployee(@ModelAttribute @Valid NguoiDungEntity employee,
                                        @RequestParam("email") String username, @RequestParam("password") String pass, BindingResult result, Model model) {

        UserJsonRespone respone = new UserJsonRespone();
        NguoiDungEntity nguoiDungEntity = nguoiDungRepository.findByTenDangNhapAndMatKhau(username, pass);

        if(nguoiDungEntity == null){

            respone.setValidated(false);
        }
        return respone;
    }

    @PostMapping(value = "/saveEmployee", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String submitLogin(@RequestParam("email") String username, @RequestParam("pass") String pass,
                              Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        NguoiDungEntity userEntity = nguoiDungRepository.findByTenDangNhapAndMatKhau(username, pass);
        boolean captchaOK = GoogleRecaptchaVerify.verify(request.getParameter("g-recaptcha-response"));
        if (userEntity == null) {

        } else {
            HttpSession session = request.getSession();
            if (captchaOK) {
                List<NguoiDung_PhanQuyenEntity> phanQuyenEntities = userEntity.getNguoiDung_phanQuyenEntities();
                for (NguoiDung_PhanQuyenEntity phanquyen : phanQuyenEntities) {

                    session.setAttribute("user", userEntity.getTenDangNhap());
                    if (phanquyen.getPhanQuyen().getQuyen().equalsIgnoreCase("Admin")) {
                        return "admin/index";
                    } else if (phanquyen.getPhanQuyen().getQuyen().equalsIgnoreCase("Teacher")) {
                        return "giaovien";
                    } else if (phanquyen.getPhanQuyen().getQuyen().equalsIgnoreCase("Student")) {
                        return "sinhvien";

                    }
                }
            } else {
                model.addAttribute("error", "");
            }

        }
        return "login";
    }








    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showAdminPage(Model model) {
        return "admin";
    }

    @RequestMapping(value = "/giaovien", method = RequestMethod.GET)
    public String showTeacherPage(Model model) {
        return "giaovien";
    }

    @RequestMapping(value = "/sinhvien", method = RequestMethod.GET)
    public String showStudentPage(Model model) {
        return "sinhvien";
    }

    @RequestMapping(value = "/subCategory", method = POST, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public Object subCategory(HttpServletRequest request) throws InterruptedException {
        Thread.sleep(500); // simulate a long processing...
        String selected = request.getParameter("depdrop_parents[0]");
        String jsonOutput = "{\n" +
                "    \"output\": [\n" +
                "        {\"id\": \"1\", \"name\": \"Học kì I\"},\n" +
                "        {\"id\": \"2\", \"name\": \"Học kì II\"}\n" +
                "    ],\n" +
                "    \"Chọn học kì\": \"1\"\n" +
                "}";
        return jsonOutput;

    }

    @RequestMapping(value = "/fogotpassword", method = RequestMethod.GET)
    public String showQuenMK(Model model) {
        return "fogot";
    }

    @RequestMapping(value = "/resetpassword", method = GET, produces = "text/plain;charset=UTF-8")
    public String forgotPassword(@RequestParam("email") String email1,
                                 Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String MY_EMAIL = "truongvancanhdtu@gmail.com";
        String MY_PASSWORD = "vancanh123";
        String FRIEND_EMAIL = email1;
        try {
            Email email = new SimpleEmail();
            Random random = new Random();
            Integer newPassword = random.nextInt(100000);
            // Cấu hình thông tin Email Server
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(MY_EMAIL, MY_PASSWORD));

            // Với gmail cái này là bắt buộc.
            email.setSSLOnConnect(true);

            // Người gửi
            email.setFrom(MY_EMAIL);

            // Tiêu đề
            email.setSubject("Reset password");

            // Nội dung email
            email.setMsg("This is your newpassword: " + newPassword.toString());
            String message = "Chúng tôi đã gửi mật khẩu mới đến: " + email1;
            // Người nhận
            email.addTo(FRIEND_EMAIL);
            email.send();
            model.addAttribute("sent", message);
            return "fogot";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}