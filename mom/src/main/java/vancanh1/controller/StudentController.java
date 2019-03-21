package vancanh1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import vancanh1.entity.*;
import vancanh1.repository.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    TieuChiRepository tieuChiRepository;

    @Autowired
    BangDanhGiaRepository bangDanhGiaRepository;

    @Autowired
    NamHocRepository namHocRepository;

    @Autowired
    HocKiRepository hocKiRepository;

    @Autowired
    DanhGiaSVRepository danhGiaSVRepository;

    @Autowired
    SinhVienRepository sinhVienRepository;

    @Autowired
    XepLoaiSinhVienRepository xepLoaiSinhVienRepository;

    @RequestMapping(value = "/chonnamhocsv", method = RequestMethod.GET)
    public String showNamHoc(Model model) {
        return "sv_chonhocki_danhgia";
    }
    NamHocEntity nh;
    List<HocKiEntity> list;
    HocKiEntity hocKiEntity = new HocKiEntity();
    @RequestMapping(value = "/namhocsv", method = RequestMethod.GET)
    public String showNamHocPage(@RequestParam("namhoc") String namhoc,
                                 @RequestParam("hocki") String hocki, Model model) {
        int check1 = 0;
        nh = namHocRepository.findByTenNH(namhoc); // tim nam hoc voi nam hoc cho truoc
        list = nh.getHocKiEntityList(); // list hoc ki bang nam hoc cham get hoc ki: gom 2 phan tu
        for (HocKiEntity hk : list) { // duyet qua 2 phan tu
            if (hk.getTenHK().equalsIgnoreCase(hocki)) { // if hoc ki bang voi ten hoc ki nhap vao
                List<TieuChiEntity> tieuChiList = new ArrayList<>();
                try {
                    tieuChiList = tieuChiRepository.findByBangDanhGiaId(hk.getBangDanhGia().getId());
                    model.addAttribute("tieuChiList", tieuChiList);
                    model.addAttribute("sizetc", tieuChiList.size());
                    hocKiEntity.setMaHK(hk.getMaHK());
                    hocKiEntity.setTenHK(hk.getTenHK());
                    hocKiEntity.setBangDanhGia(hk.getBangDanhGia());
                    hocKiEntity.setNamHoc(hk.getNamHoc());
                    model.addAttribute("hocki", hocKiEntity);
                    return "sv_bangdanhgia";
                }catch (NullPointerException ex){
                    check1 = 1;
                    model.addAttribute("check1",check1);
                    return "sv_chonhocki_danhgia";
                }

            }

        }
        return "redirect:/sinhvien";
    }
    @RequestMapping(value = "/danhgiasv", method = RequestMethod.GET)
    public String SaveDGSV(@RequestParam("tongdiem1") String tongdiem,Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        SinhVienEntity sinhVienEntity = (SinhVienEntity) session.getAttribute("sinhvien");
        DanhGiaSVEntity dgSV = new DanhGiaSVEntity();
        dgSV.setTongDiem(Integer.valueOf(tongdiem));
        dgSV.setBangDanhGia(hocKiEntity.getBangDanhGia());
        dgSV.setSinhVien(sinhVienEntity);
        DanhGiaSVEntity dgsinhVienEntity1 = danhGiaSVRepository.findBySinhVienAndBangDanhGia(sinhVienEntity,hocKiEntity.getBangDanhGia());
        //RankStudentEntity rank = (RankStudentEntity) xepLoaiSinhVienRepository.findBySinhVienAndHocKi(sinhVienEntity,hocKiEntity);
        //if(rank != null){
          //  xepLoaiSinhVienRepository.delete(rank);
        //}
        if(dgsinhVienEntity1 != null){
            danhGiaSVRepository.delete(dgsinhVienEntity1);
            danhGiaSVRepository.save(dgSV);

        }else{

            danhGiaSVRepository.save(dgSV);
        }

        model.addAttribute("check",1);
        showNamHocPage(nh.getTenNH(),hocKiEntity.getTenHK(),model);
        return "sv_bangdanhgia";
    }
    @RequestMapping(value = "/dangxuat", method = RequestMethod.GET)
    public String dangXuatSV(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("sinhvien");
        session.removeAttribute("giaovien");
        session.removeAttribute("admin");
        return "redirect:/";
    }


    @RequestMapping(value = "/thongtincanhan_sv", method = RequestMethod.GET)
    public String showStudentInform(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        SinhVienEntity sinhVienEntity = (SinhVienEntity) session.getAttribute("sinhvien");
        model.addAttribute("sv",sinhVienEntity);
        return "thongtinsinhvien";
    }





}
