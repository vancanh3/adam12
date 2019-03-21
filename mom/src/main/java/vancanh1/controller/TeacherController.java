package vancanh1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vancanh1.entity.*;
import vancanh1.repository.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class TeacherController {
    @Autowired
    TieuChiRepository tieuChiRepository;

    @Autowired
    NamHocRepository namHocRepository;

    @Autowired
    HocKiRepository hocKiRepository;

    @Autowired
    LopRepository lopRepository;

    @Autowired
    SinhVienRepository sinhVienRepository;

    @Autowired
    GiangVienRepository giangVienRepository;

    @Autowired
    DanhGiaSVRepository danhGiaSVRepository;

    @Autowired
    XepLoaiSinhVienRepository xepLoaiSinhVienRepository;

    @RequestMapping(value = "/chonnamhocgv", method = GET)
    public String showNamHoc(Model model) {
        return "gv_chonhocki_danhgia";
    }


    HocKiEntity hocKiEntity = new HocKiEntity();
    BangDanhGiaEntity bangDanhGiaEntity = new BangDanhGiaEntity();

    //Liệt kê các lớp cho giảng viên chọn để đánh giá
    @RequestMapping(value = "/namhocgv", method = GET)
    public String showLopGV(@RequestParam("namhoc") String namhoc,
                            @RequestParam("hocki") String hocki, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        GiaoVienEntity gv = (GiaoVienEntity) session.getAttribute("giaovien");
        List<LopEntity> lopEntityList = timKiemLop(gv.getMaGV());
        model.addAttribute("namhoc", namhoc);
        model.addAttribute("hocki", hocki);
        model.addAttribute("dslop", lopEntityList);
        return "gv_chonlophoc_danhgia";
    }
    LopEntity lopEntity = new LopEntity();
    //Lấy ra ds sinh viên đã đánh giá
    @RequestMapping(value = "/danhgialop", method = GET)
    public String showNamHocPage(@RequestParam("namhoc") String namhoc,
                                 @RequestParam("hocki") String hocki, @RequestParam("lopId") String malop, Model model) {
        List<DiemDanhGiaSV> daDGSV = new ArrayList<>();
        List<DiemDanhGiaSV> diemSV = new ArrayList<>();
        hocKiEntity = timKiemHK(namhoc, hocki);

        bangDanhGiaEntity = hocKiEntity.getBangDanhGia();
        DanhGiaSVEntity danhGiaSV = new DanhGiaSVEntity();
        LopEntity lop = lopRepository.findOne(malop);
        lopEntity = lop;

        List<SinhVienEntity> listSV = sinhVienRepository.findByLop(lop); // Lay ds sinh vien co trong lop hien tai
        for (SinhVienEntity sv : listSV) {
            DanhGiaSVEntity dgSV = danhGiaSVRepository.findBySinhVienAndBangDanhGia(sv,bangDanhGiaEntity);
            DiemDanhGiaSV diem = new DiemDanhGiaSV();
            diem.setMaSV(sv.getMaSV());
            diem.setTenSV(sv.getTenSV());
            if(dgSV != null){
                diem.setTongDiem(dgSV.getTongDiem());
            }
            else{
                diem.setTongDiem(0);
            }
            RankStudentEntity rank = xepLoaiSinhVienRepository.findByHocKiAndSinhVien(hocKiEntity,sv);
            if(rank == null){
                diemSV.add(diem);
            }
            else{
                diem.setTongDiem(rank.getTongDiem());
                daDGSV.add(diem);
            }

        }
        model.addAttribute("namhoc", namhoc);
        model.addAttribute("hocki", hocki);
        model.addAttribute("lopId",malop);
        model.addAttribute("listSv", diemSV);
        model.addAttribute("daDG",daDGSV);
        return "gv_dssinhvien";


    }


    String maSinhVien = "";
    //Trả lại trang đánh giá cho giảng viên đánh giá
    @RequestMapping(value = "/taodanhgiagv", method = GET)
    public String showDanhGiaGVPage(@RequestParam("masv") String maSV, Model model) {
        SinhVienEntity sv = sinhVienRepository.findByMaSV(maSV);
        RankStudentEntity rank = xepLoaiSinhVienRepository.findByHocKiAndSinhVien(hocKiEntity,sv);
        List<TieuChiEntity> tieuChiList = tieuChiRepository.findByBangDanhGiaId(bangDanhGiaEntity.getId());
        model.addAttribute("tieuChiList", tieuChiList);
        model.addAttribute("sizetc", tieuChiList.size());
        maSinhVien = maSV;
        model.addAttribute("masv", maSV);
        return "gv_bangdanhgia";

    }

    //Giảng viên đánh giá mỗi sinh viên
    @RequestMapping(value = "/danhgiagiaovien", method = GET)
    public String setStudentRank(@RequestParam("tongdiem1") String tongdiem, Model model) {
        int tongDiem = Integer.valueOf(tongdiem);
        RankStudentEntity rank = new RankStudentEntity();
        rank.setHocKi(hocKiEntity);
        SinhVienEntity sv = sinhVienRepository.findByMaSV(maSinhVien);
        rank.setSinhVien(sv);
        rank.setTongDiem(tongDiem);
        String loai = "";
        if (tongDiem == 0) {
            loai = "Kém";
        } else if (tongDiem < 50) {
            loai = "Yếu";
        } else if (tongDiem >= 50 && tongDiem < 65) {
            loai = "Trung bình";
        } else if (tongDiem >= 65 && tongDiem < 80) {
            loai = "Khá";
        } else if (tongDiem >= 80 && tongDiem < 94) {
            loai = "Giỏi";
        } else {
            loai = "Xuất sắc";
        }
        rank.setXepLoai(loai);
        RankStudentEntity xeploai = xepLoaiSinhVienRepository.findByHocKiAndSinhVien(hocKiEntity,sv);
        if(xeploai != null){
            xepLoaiSinhVienRepository.delete(xeploai);
            xepLoaiSinhVienRepository.save(rank);
        }else{
            xepLoaiSinhVienRepository.save(rank);
        }
        model.addAttribute("check",1);
        showDanhGiaGVPage(maSinhVien,model);
        return "gv_bangdanhgia";


    }

    List<LopEntity> lop = new ArrayList<>();
    //Chọn học kì và năm học để thống kê
    @RequestMapping(value = "/thongkexl", method = GET)
    public String RankStatistic(Model model) {
        return "gv_chonhocki_thongke";
    }
    //Chọn lớp học để thống kê xếp loại mỗi kỳ
    @RequestMapping(value = "/hocki", method = GET)
    public String showNamHoc(@RequestParam("namhoc") String namhoc,
                             @RequestParam("hocki") String hocki, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        GiaoVienEntity gv = (GiaoVienEntity) session.getAttribute("giaovien");
        List<LopEntity> lopEntityList = timKiemLop(gv.getMaGV());
        model.addAttribute("namhoc", namhoc);
        model.addAttribute("hocki", hocki);
        model.addAttribute("dslop", lopEntityList);
        return "gv_chonlophoc_thongke";
    }

    //Thống kê xếp loại sinh viên mỗi kỳ
    @RequestMapping(value = "/thongkelop", method = GET)
    public String showThongKe(@RequestParam("namhoc_thongke") String namhoc,
                              @RequestParam("hocki_thongke") String hocki, @RequestParam("lopId") String maLop, Model model) {
        List<RankStudentEntity> tongxl = new ArrayList<>();
        int gioi = 0, kha = 0, trungbinh = 0, yeu = 0, xuatsac = 0, kem = 0;
        double tylegioi = 0, tylekha = 0, tyletrungbinh = 0, tyleyeu = 0, tylexuatsac = 0, tylekem = 0;
        HocKiEntity hk = timKiemHK(namhoc, hocki);
        LopEntity lopEntity = lopRepository.findOne(maLop);
        double tongdiem = 0;
        List<SinhVienEntity> listSV = sinhVienRepository.findByLop(lopEntity);
        for (SinhVienEntity sv : listSV) {
            RankStudentEntity rankStudentEntity = xepLoaiSinhVienRepository.findByHocKiAndSinhVien(hk, sv);
            if (rankStudentEntity != null) {
                tongdiem += rankStudentEntity.getTongDiem();
                String loai = rankStudentEntity.getXepLoai();
                if (loai.equalsIgnoreCase("Xuất sắc")) {
                    xuatsac++;
                } else if (loai.equalsIgnoreCase("Giỏi")) {
                    gioi++;
                } else if (loai.equalsIgnoreCase("Khá")) {
                    kha++;
                } else if (loai.equalsIgnoreCase("Trung Bình")) {
                    trungbinh++;
                } else {
                    yeu++;
                }
                tongxl.add(rankStudentEntity);
            } else {
                RankStudentEntity rank = new RankStudentEntity();
                rank.setHocKi(hk);
                rank.setTongDiem(0);
                rank.setXepLoai("Kém");
                kem++;
                rank.setSinhVien(sv);
                tongxl.add(rank);
            }

        }
        int soLuongSV = listSV.size();
        tylexuatsac = (double) xuatsac / soLuongSV * 100;
        tylegioi = (double) gioi / soLuongSV * 100;
        tylekha = (double) kha / soLuongSV * 100;
        tyletrungbinh = (double) trungbinh / soLuongSV * 100;
        tyleyeu = (double) yeu / soLuongSV * 100;
        tylekem = (double) kem / soLuongSV * 100;
        model.addAttribute("xuatsac", xuatsac);
        model.addAttribute("gioi", gioi);
        model.addAttribute("kha", kha);
        model.addAttribute("trungbinh", trungbinh);
        model.addAttribute("yeu", yeu);
        model.addAttribute("kem", kem);
        model.addAttribute("tylegioi", tylegioi);
        model.addAttribute("tylekha", tylekha);
        model.addAttribute("tyletrungbinh", tyletrungbinh);
        model.addAttribute("tyleyeu", tyleyeu);
        model.addAttribute("tylekem", tylekem);
        model.addAttribute("tylexuatsac", tylexuatsac);
        model.addAttribute("tongxl", tongxl);
        model.addAttribute("tenlop", lopEntity);
        model.addAttribute("namhoc", hk.getNamHoc().getTenNH());
        model.addAttribute("hocki", hk.getTenHK());
        model.addAttribute("tongdiem",tongdiem);
        return "thongkegv";
    }
    //tìm kiếm lớp
    public List<LopEntity> timKiemLop(String giaovienId) {
        List<LopEntity> lopEntityList = new ArrayList<>();
        GiaoVienEntity giaoVienEntity = timkiemGV(giaovienId);
        try {
            lopEntityList = lopRepository.findByGiaoVien(giaoVienEntity); // tim ds cac lop co gv chu nhiem la gv1

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return lopEntityList;
    }
    //Tìm kiếm học kì
    public HocKiEntity timKiemHK(String namhoc, String hocki) {
        NamHocEntity nh = namHocRepository.findByTenNH(namhoc);
        List<HocKiEntity> list = nh.getHocKiEntityList();
        for (HocKiEntity hk : list) { // duyet qua 2 phan tu
            if (hk.getTenHK().equalsIgnoreCase(hocki)) { // if hoc ki bang voi ten hoc ki nhap vao
                return hk;
            }
        }
        return null;
    }
    //Chọn chức năng thông kê học sinh cả năm
    @RequestMapping(value = "/thongkecanam", method = GET)
    public String showThongKe(Model model) {
        return "gv_chonnamhoc_thongkecanam";
    }
    //Chọn lớp học để thống kê xếp loại  sinh viên mỗi năm
    @RequestMapping(value = "/thongkenamhoc", method = GET)
    public String showThongKe(@RequestParam("namhoc") String namhoc, Model model,HttpServletRequest request) {
        HttpSession session = request.getSession();
        GiaoVienEntity giaovien = (GiaoVienEntity) session.getAttribute("giaovien");
        NamHocEntity nh = namHocRepository.findByTenNH(namhoc);
        List<LopEntity> lopEntityList = timKiemLop(giaovien.getMaGV());
        model.addAttribute("namhoc", namhoc);
        model.addAttribute("dslop", lopEntityList);
        return "gv_chonlophoc_thongkecanam";
    }
    //Tìm kiếm giáo viên
    public GiaoVienEntity timkiemGV(String gv) {
        GiaoVienEntity giaoVien;
        List<GiaoVienEntity> giaoVienEntities = (List<GiaoVienEntity>) giangVienRepository.findAll();
        for (GiaoVienEntity giaoVienEntity : giaoVienEntities) {
            if (giaoVienEntity.getMaGV().equalsIgnoreCase(gv)) {
                giaoVien = giaoVienEntity;
                return giaoVien;
            }
        }
        return null;
    }
    //Thống kê tổng điểm sinh viên mỗi năm
    @RequestMapping(value = "/thongkelopcanam", method = GET)
    public String showThongKeCaNam(@RequestParam("namhoc") String namhoc, @RequestParam("lopId") String maLop, Model model) {
        NamHocEntity nh = namHocRepository.findByTenNH(namhoc);
        List<ThongKeCaNam> thongKeCaNamList = new ArrayList<>();
        LopEntity lop = lopRepository.findOne(maLop);
        double tongdiem1 = 0;
        List<SinhVienEntity> listSV = sinhVienRepository.findByLop(lop);
        for (SinhVienEntity sv : listSV) {
            ThongKeCaNam thongKeCaNam = new ThongKeCaNam();
            List<RankStudentEntity> listXL = xepLoaiSinhVienRepository.findBySinhVien(sv);
            if(listXL.size() > 0) {
                for (int i = 0; i < listXL.size(); i++) {
                    if (listXL.get(i).getHocKi().getNamHoc().getMaNH().equalsIgnoreCase(nh.getMaNH())) {

                    }else{
                        listXL.remove(i);
                        i--;
                    }
                }
            }
            else{
                thongKeCaNam.setDiemHKI(0);
                thongKeCaNam.setXeploaiHKI("Không Đánh Giá");
                thongKeCaNam.setDiemHKII(0);
                thongKeCaNam.setXeploaiHKII("Không Đánh Giá");
            }

            thongKeCaNam.setSinhVien(sv);
            if (listXL.size() == 2) {
                for (RankStudentEntity rank : listXL) {
                    if(rank.getHocKi().getNamHoc().getTenNH().equalsIgnoreCase(nh.getTenNH())) {
                        tongdiem1 += rank.getTongDiem();
                        if (rank.getHocKi().getTenHK().equalsIgnoreCase("Học kì I")) {
                            thongKeCaNam.setDiemHKI(rank.getTongDiem());
                            thongKeCaNam.setXeploaiHKI(rank.getXepLoai());
                        } else if (rank.getHocKi().getTenHK().equalsIgnoreCase("Học kì II")) {
                            thongKeCaNam.setDiemHKII(rank.getTongDiem());
                            thongKeCaNam.setXeploaiHKII(rank.getXepLoai());
                        }
                    }
                }
            } else if (listXL.size() == 1) {
                for (RankStudentEntity rank : listXL) {
                    if(rank.getHocKi().getNamHoc().getTenNH().equalsIgnoreCase(nh.getTenNH())) {
                        tongdiem1 += rank.getTongDiem();
                        if (rank.getHocKi().getTenHK().equalsIgnoreCase("Học kì I")) {
                            thongKeCaNam.setDiemHKI(rank.getTongDiem());
                            thongKeCaNam.setXeploaiHKI(rank.getXepLoai());
                            thongKeCaNam.setDiemHKII(0);
                            thongKeCaNam.setXeploaiHKII("Không Đánh Giá");
                        } else if (rank.getHocKi().getTenHK().equalsIgnoreCase("Học kì II")) {
                            thongKeCaNam.setDiemHKII(rank.getTongDiem());
                            thongKeCaNam.setXeploaiHKII(rank.getXepLoai());
                            thongKeCaNam.setDiemHKI(0);
                            thongKeCaNam.setXeploaiHKI("Không Đánh Giá");
                        }
                    }
                }
            } else {
                thongKeCaNam.setDiemHKI(0);
                thongKeCaNam.setXeploaiHKI("Không Đánh Giá");
                thongKeCaNam.setDiemHKII(0);
                thongKeCaNam.setXeploaiHKII("Không Đánh Giá");
            }
            double tongdiem = (double) (thongKeCaNam.getDiemHKI() + thongKeCaNam.getDiemHKII()) / 2;
            String loai = "";
            if (tongdiem == 0) {
                loai = "Kém";
            } else if (tongdiem < 50) {
                loai = "Yếu";
            } else if (tongdiem >= 50 && tongdiem < 65) {
                loai = "Trung bình";
            } else if (tongdiem >= 65 && tongdiem < 79) {
                loai = "Khá";
            } else if (tongdiem >= 80 && tongdiem < 94) {
                loai = "Giỏi";
            } else {
                loai = "Xuất sắc";
            }
            thongKeCaNam.setDiemCN(tongdiem);
            thongKeCaNam.setXeploaiCN(loai);
            thongKeCaNamList.add(thongKeCaNam);
        }

        model.addAttribute("xeploaiCN", thongKeCaNamList);
        model.addAttribute("tenlop", lop.getTenLop());
        model.addAttribute("namhoc", nh.getTenNH());
        model.addAttribute("tongdiem",tongdiem1);


        return "gv_thongkexeploai_canam";
    }

    //Chọn năm học cho thống kế sinh viên đánh giá
    @RequestMapping(value = "/thongkesv", method = GET)
    public String StudentStatictis(Model model) {
        return "gv_chonhocki_thongkesv";
    }
    //Chọn lớp học để thống kê số lượng sinh viên đánh giá
    @RequestMapping(value = "/thongkesv1", method = GET)
    public String showLopHoc(@RequestParam("namhoc") String namhoc, @RequestParam("hocki") String hocki, Model model,
                             HttpServletRequest request) {
        NamHocEntity nh = namHocRepository.findByTenNH(namhoc);
        HttpSession session = request.getSession();
        GiaoVienEntity giaovien = (GiaoVienEntity) session.getAttribute("giaovien");
        List<LopEntity> lopEntityList = timKiemLop(giaovien.getMaGV());
        model.addAttribute("namhoc", namhoc);
        model.addAttribute("hocki", hocki);
        model.addAttribute("dslop", lopEntityList);
        return "gv_chonlophoc_thongkesv";
    }

    //Thống kê số lượng sinh viên không đánh giá và đánh giá
    @RequestMapping(value = "/thongkelopsv", method = GET)
    public String thongKeLopSV(@RequestParam("namhoc") String namhoc, @RequestParam("hocki") String hocki,
                               @RequestParam("lopId") String malop, Model model) {
        LopEntity lopEntity = lopRepository.findOne(malop);
        List<SinhVienEntity> sinhVienChuaDanhGia = new ArrayList<>();
        HocKiEntity hk = timKiemHK(namhoc, hocki);
        List<SinhVienEntity> listSV = sinhVienRepository.findByLop(lopEntity);
        List<RankStudentEntity> listXL = xepLoaiSinhVienRepository.findByHocKi(hk);
        for (SinhVienEntity sv: listSV){
            RankStudentEntity rank = xepLoaiSinhVienRepository.findByHocKiAndSinhVien(hk,sv);
            if (rank == null){
                sinhVienChuaDanhGia.add(sv);
            }
        }
        model.addAttribute("svdg",listXL);
        model.addAttribute("svcdg",sinhVienChuaDanhGia);
        model.addAttribute("sldg",listXL.size());
        model.addAttribute("slcdg",sinhVienChuaDanhGia.size());
        model.addAttribute("namhoc",hk.getNamHoc().getTenNH());
        model.addAttribute("hocki",hk.getTenHK());
        return "gv_thongkesv_danhgia";
    }
    public static final int dataPerPage = 3;
    public List<RankStudentEntity> getRanks(int page){
        // simulate paging, you can replace by MySQL data retrieving for example.
        List<RankStudentEntity> tongxl = new ArrayList<>();
        List<RankStudentEntity> result = new ArrayList<>();
        int start = (page - 1) * dataPerPage;
        for (int i = start; i < start + dataPerPage; i++) {
            result.add(tongxl.get(i));
        }
        if(result.size() < dataPerPage){
            for (int i = 0; i < dataPerPage - result.size(); i++){
                result.add(tongxl.get(i));
            }
        }
        return result;
    }
    @RequestMapping(value = "/rank/{page}", method = GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getData(@PathVariable int page) {
        String result = "";
        List<RankStudentEntity> data = getRanks(page);
        for (RankStudentEntity rank: data) {
            result += "<tr><td>" + rank.getSinhVien().getMaSV() + "</td>" +
                    "<td>" + rank.getSinhVien().getTenSV() + "</td>" +
                    "<td>" + rank.getSinhVien().getNgaysinh() + "</td>" +
                    "<td>" + rank.getTongDiem() + "</td>" +
                    "<td>" + rank.getXepLoai() + "</td></tr>";
        }

        return result;
    }


}




