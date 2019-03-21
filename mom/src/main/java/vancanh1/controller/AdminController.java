package vancanh1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import vancanh1.entity.*;
import vancanh1.repository.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class AdminController {
    @Autowired
    TieuChiRepository tieuChiRepository;

    @Autowired
    PhanQuyenRepository phanQuyenRepository;

    @Autowired
    BangDanhGiaRepository bangDanhGiaRepository;

    @Autowired
    NamHocRepository namHocRepository;

    @Autowired
    HocKiRepository hocKiRepository;

    @Autowired
    KhoaRepository khoaRepository;

    @Autowired
    NganhHocRepository nganhHocRepository;

    @Autowired
    LopRepository lopRepository;

    @Autowired
    SinhVienRepository sinhVienRepository;

    @Autowired
    GiangVienRepository giangVienRepository;

    @Autowired
    NguoiDungRepository nguoiDungRepository;
    @Autowired
    XepLoaiSinhVienRepository xepLoaiSinhVienRepository;
    @RequestMapping(value = "/taodanhgia", method = GET)
    public String taoDanhGia(){
        return "ad_chonhocki_taodanhgia";
    }

    @RequestMapping(value = "/chonnamhoc", method = RequestMethod.GET)
    public String showNamHocPage(@RequestParam("namhoc") String namhoc,
                                 @RequestParam("hocki") String hocki, Model model) {
        BangDanhGiaEntity danhGiaEntity = new BangDanhGiaEntity(); //tao ra 1 doi tuong bang danh gia
        NamHocEntity nh = namHocRepository.findByTenNH(namhoc); // tim nam hoc voi nam hoc cho truoc
        List<HocKiEntity> list = nh.getHocKiEntityList(); // list hoc ki bang nam hoc cham get hoc ki: gom 2 phan
        for (HocKiEntity hk : list) { // duyet qua 2 phan tu
            if (hk.getTenHK().equalsIgnoreCase(hocki)) { // if hoc ki bang voi ten hoc ki nhap vao
                int k = 0; // tao ra 1 bien de kiem tra id cua bang danh gia
                try {
                    danhGiaEntity.setHocKi(hk); // set id cho cai bang danh gia vua tao o tren
                    k = hk.getBangDanhGia().getId(); // set k = hk get id
                    if (k != 0) { // la co bang danh gia
                        //tao ra 1 list tieu chi lay duoc dua vao id cua bang danh gia
                        List<TieuChiEntity> tieuChiList = tieuChiRepository.findByBangDanhGiaId(hk.getBangDanhGia().getId());
                        model.addAttribute("tieuChiList", tieuChiList);
                        model.addAttribute("sizetc", tieuChiList.size());
                        return "ad_dacobangdanhgia";
                    }
                }catch (NullPointerException r){

                }
            }
        }
        //luu
        
        model.addAttribute("namhoc",namhoc);
        model.addAttribute("hocki", hocki);
        return "ad_taodanhgia";
    }


    //Tạo tiêu chí
    @RequestMapping(value = "/addtieuchi", method = GET)
    public String EvaluationPage(@RequestParam("tieuchi10") String tieuchi10,
                                 @RequestParam("diem10") String diem10,
                                 @RequestParam("tieuchi1") String tieuchi1,
                                 @RequestParam("diem1") String diem1,
                                 @RequestParam("tieuchi2") String tieuchi2,
                                 @RequestParam("diem2") String diem2,
                                 @RequestParam("tieuchi3") String tieuchi3,
                                 @RequestParam("diem3") String diem3,
                                 @RequestParam("tieuchi4") String tieuchi4,
                                 @RequestParam("diem4") String diem4,
                                 @RequestParam("tieuchi5") String tieuchi5,
                                 @RequestParam("diem5") String diem5,
                                 @RequestParam("tieuchi6") String tieuchi6,
                                 @RequestParam("diem6") String diem6,
                                 @RequestParam("tieuchi8") String tieuchi8,
                                 @RequestParam("diem8") String diem8,
                                 @RequestParam("tieuchi9") String tieuchi9,
                                 @RequestParam("diem9") String diem9,
                                 @RequestParam("tieuchi7") String tieuchi7,
                                 @RequestParam("diem7") String diem7,
                                 @RequestParam("namhoc") String namhoc,
                                 @RequestParam("hocki") String hocki,Model model
    ) {
        NamHocEntity nh = namHocRepository.findByTenNH(namhoc); // tim nam hoc voi nam hoc cho truoc
        List<HocKiEntity> list = nh.getHocKiEntityList(); // list hoc ki bang nam hoc cham get hoc ki: gom 2 phan
        BangDanhGiaEntity bangDanhGiaEntity = new BangDanhGiaEntity();
        for (HocKiEntity hk : list) { // duyet qua 2 phan tu
            if (hk.getTenHK().equalsIgnoreCase(hocki)) { // if hoc ki bang voi ten hoc ki nhap vao
                bangDanhGiaEntity.setHocKi(hk);
                bangDanhGiaRepository.save(bangDanhGiaEntity);
            }
        }

        //tieu chi 1
        String[] s1 = diem1.split("[,]");
        int d1 = Integer.parseInt(s1[0]);
        TieuChiEntity tc1 = new TieuChiEntity();
        tc1.setTenTC(tieuchi1);
        tc1.setDiem(d1);
        tc1.setBangDanhGia(bangDanhGiaEntity);
        tieuChiRepository.save(tc1);

        //tieu chi 2
        String[] s2 = diem2.split("[,]");
        int d2 = Integer.parseInt(s2[0]);
        TieuChiEntity tc2 = new TieuChiEntity();
        tc2.setTenTC(tieuchi2);
        tc2.setDiem(d2);
        tc2.setBangDanhGia(bangDanhGiaEntity);
        tieuChiRepository.save(tc2);

        //tieu chi 3
        String[] s3 = diem1.split("[,]");
        int d3 = Integer.parseInt(s3[0]);
        TieuChiEntity tc3 = new TieuChiEntity();
        tc3.setTenTC(tieuchi3);
        tc3.setDiem(d3);
        tc3.setBangDanhGia(bangDanhGiaEntity);
        tieuChiRepository.save(tc3);

        //tieu chi 4
        String[] s4 = diem1.split("[,]");
        int d4 = Integer.parseInt(s4[0]);
        TieuChiEntity tc4 = new TieuChiEntity();
        tc4.setTenTC(tieuchi4);
        tc4.setDiem(d4);
        tc4.setBangDanhGia(bangDanhGiaEntity);
        tieuChiRepository.save(tc4);

        //tieu chi 5
        String[] s5 = diem5.split("[,]");
        int d5 = Integer.parseInt(s5[0]);
        TieuChiEntity tc5 = new TieuChiEntity();
        tc5.setTenTC(tieuchi5);
        tc5.setDiem(d5);
        tc5.setBangDanhGia(bangDanhGiaEntity);
        tieuChiRepository.save(tc5);

        //tieu chi 6
        String[] s6 = diem1.split("[,]");
        int d6 = Integer.parseInt(s3[0]);
        TieuChiEntity tc6 = new TieuChiEntity();
        tc6.setTenTC(tieuchi6);
        tc6.setDiem(d6);
        tc6.setBangDanhGia(bangDanhGiaEntity);
        tieuChiRepository.save(tc6);

        //tieu chi 7
        String[] s7 = diem1.split("[,]");
        int d7 = Integer.parseInt(s7[0]);
        TieuChiEntity tc7 = new TieuChiEntity();
        tc7.setTenTC(tieuchi7);
        tc7.setDiem(d7);
        tc7.setBangDanhGia(bangDanhGiaEntity);
        tieuChiRepository.save(tc7);

        //tieu chi 8
        String[] s8 = diem1.split("[,]");
        int d8 = Integer.parseInt(s8[0]);
        TieuChiEntity tc8 = new TieuChiEntity();
        tc8.setTenTC(tieuchi8);
        tc8.setDiem(d8);
        tc8.setBangDanhGia(bangDanhGiaEntity);
        tieuChiRepository.save(tc8);

        //tieu chi 9
        String[] s9 = diem1.split("[,]");
        int d9 = Integer.parseInt(s9[0]);
        TieuChiEntity tc9 = new TieuChiEntity();
        tc9.setTenTC(tieuchi9);
        tc9.setDiem(d9);
        tc9.setBangDanhGia(bangDanhGiaEntity);
        tieuChiRepository.save(tc9);

        //tieu chi 10
        String[] s10 = diem5.split("[,]");
        int d10 = Integer.parseInt(s10[0]);
        TieuChiEntity tc10 = new TieuChiEntity();
        tc10.setTenTC(tieuchi10);
        tc10.setDiem(d10);
        tc10.setBangDanhGia(bangDanhGiaEntity);
        tieuChiRepository.save(tc10);
        model.addAttribute("check",1);
        return "ad_chonhocki_thongkehocki";
    }

    //Thống kê học kì
    @RequestMapping(value = "/thongkekhoa_hocki", method = GET)
    public String chonHocKi_TK(){
        return "ad_chonhocki_thongkehocki";
    }

    @RequestMapping(value = "/thongkekhoatheoki", method = GET)
    public String thongkeKhoa_Hocki(@RequestParam("namhoc") String namhoc, @RequestParam("hocki") String hocki,Model model){
        List<KhoaEntity> list = getKhoaList();
        model.addAttribute("listKhoa",list);
        model.addAttribute("namhoc", namhoc);
        model.addAttribute("hocki", hocki);
        return "ad_chonkhoa_thongkexeploai";
    }
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
    @RequestMapping(value = "/thongkekhoaxl", method = GET)
    public String thongKeLopSV(@RequestParam("namhoc") String namhoc, @RequestParam("hocki") String hocki,
                               @RequestParam("khoaId") String khoa, Model model){
        HocKiEntity hocKiEntity = timKiemHK(namhoc,hocki);
        List<Khoa_ThongKeHocKi> listTK = new ArrayList<>();
        KhoaEntity khoaEntity = khoaRepository.findOne(khoa);
        double tyleXS, tyleG, tyleK, tyleTB,tyleY,tyleKem;
        int tongdiem = 0;
        List<NganhHocEntity> nganhHocEntityList = nganhHocRepository.findByKhoa(khoaEntity);
        for (NganhHocEntity nganh: nganhHocEntityList){
            List<LopEntity> lopEntityList = lopRepository.findByNganh(nganh);
            for(LopEntity lop: lopEntityList){
                Khoa_ThongKeHocKi khoa_thongKeHocKi = new Khoa_ThongKeHocKi();
                List<SinhVienEntity> sinhVienEntityList = sinhVienRepository.findByLop(lop);
                khoa_thongKeHocKi.setSoLuongSV(sinhVienEntityList.size());
                khoa_thongKeHocKi.setLopEntity(lop);
                for (SinhVienEntity sinhVienEntity: sinhVienEntityList){
                    RankStudentEntity rank = xepLoaiSinhVienRepository.findByHocKiAndSinhVien(hocKiEntity,sinhVienEntity);
                    if(rank == null){
                        khoa_thongKeHocKi.setSlKem(khoa_thongKeHocKi.getSlKem() + 1);
                    }
                    else{
                        tongdiem += rank.getTongDiem();
                        if(rank.getTongDiem() < 50){
                            khoa_thongKeHocKi.setSlY(khoa_thongKeHocKi.getSlY() + 1);
                        }else if(rank.getTongDiem() >= 50 && rank.getTongDiem() < 65 ){
                            khoa_thongKeHocKi.setSlTB(khoa_thongKeHocKi.getSlTB() + 1);
                        }else if(rank.getTongDiem() >= 65 && rank.getTongDiem() < 80 ){
                            khoa_thongKeHocKi.setSlK(khoa_thongKeHocKi.getSlK() + 1);
                        }else if(rank.getTongDiem() >= 80 && rank.getTongDiem() < 95 ){
                            khoa_thongKeHocKi.setSlG(khoa_thongKeHocKi.getSlG() + 1);
                        }else{
                            khoa_thongKeHocKi.setSlXS(khoa_thongKeHocKi.getSlXS() + 1);
                        }
                    }
                }
                int sl = sinhVienEntityList.size();
                tyleXS = (double)khoa_thongKeHocKi.getSlXS()/sl * 100;
                tyleG = (double)khoa_thongKeHocKi.getSlG()/sl * 100;
                tyleK = (double)khoa_thongKeHocKi.getSlK()/sl * 100;
                tyleTB = (double)khoa_thongKeHocKi.getSlTB()/sl * 100;
                tyleY = (double)khoa_thongKeHocKi.getSlY()/sl * 100;
                tyleKem = (double)khoa_thongKeHocKi.getSlKem()/sl * 100;
                khoa_thongKeHocKi.setTyleXS(tyleXS);
                khoa_thongKeHocKi.setTyleG(tyleG);
                khoa_thongKeHocKi.setTyleK(tyleK);
                khoa_thongKeHocKi.setTyleTB(tyleTB);
                khoa_thongKeHocKi.setTyleY(tyleY);
                khoa_thongKeHocKi.setTyleKem(tyleKem);
                listTK.add(khoa_thongKeHocKi);
            }
        }
        model.addAttribute("tongdiem",tongdiem);
        model.addAttribute("namhoc",namhoc);
        model.addAttribute("hocki",hocki);
        model.addAttribute("khoa",khoaEntity.getTenKhoa());
        model.addAttribute("listTK",listTK);
        return "ad_thongkexeploaikhoa";
    }


    @RequestMapping(value = "/thongke", method = GET)
    public String thongKe(){
        return "ad_thongke";
    }
    //Thống ke năm học
    @RequestMapping(value = "/tkxeploai_nh", method = GET)
    public String chonNH_TK(){
        return "ad_chonnamhoc_thongkekhoa";
    }
    @RequestMapping(value = "/ad_tkxeploai_nh", method = GET)
    public String thongkeKhoa_NH(@RequestParam("namhoc") String namhoc,Model model){
        List<KhoaEntity> list = getKhoaList();
        model.addAttribute("listKhoa",list);
        model.addAttribute("namhoc", namhoc);
        return "ad_chonkhoa_thongkexeploai_nh";
    }

    @RequestMapping(value = "/thongkekhoaxl_nh", method = GET)
    public String thongKeLopSV(@RequestParam("namhoc") String namhoc,
                               @RequestParam("khoaId") String khoaId, Model model){
        NamHocEntity nh = namHocRepository.findByTenNH(namhoc);
        KhoaEntity khoa = khoaRepository.findOne(khoaId);
        List<Khoa_ThongKeHocKi> list = new ArrayList<>();
        List<NganhHocEntity> listNganh = nganhHocRepository.findByKhoa(khoa);
        double tyleXS, tyleG, tyleK, tyleTB,tyleY,tyleKem;
        int diem = 0;
        double dtb;
        for(NganhHocEntity nganh: listNganh){
            List<LopEntity> listLop = lopRepository.findByNganh(nganh);
            for(LopEntity lopEntity: listLop){
                Khoa_ThongKeHocKi khoa_thongKeHocKi = new Khoa_ThongKeHocKi();
                khoa_thongKeHocKi.setLopEntity(lopEntity);
                List<SinhVienEntity> sinhVienEntityList = sinhVienRepository.findByLop(lopEntity);
                khoa_thongKeHocKi.setSoLuongSV(sinhVienEntityList.size());
                for(SinhVienEntity sv: sinhVienEntityList){
                    List<RankStudentEntity> listXL = xepLoaiSinhVienRepository.findBySinhVien(sv);
                    if(listXL.size() > 0) {
                        for (int i = 0; i < listXL.size(); i++) {
                            if (listXL.get(i).getHocKi().getNamHoc().getMaNH().equalsIgnoreCase(nh.getMaNH())) {

                            }else{
                                listXL.remove(i);
                                i--;
                            }
                        }
                        int tongdiem = 0;
                        for(RankStudentEntity xeploai: listXL){
                            tongdiem +=xeploai.getTongDiem();
                            diem += tongdiem;
                        }
                        dtb = (double)tongdiem/2;
                        if(dtb == 0){
                            khoa_thongKeHocKi.setSlKem(khoa_thongKeHocKi.getSlKem() + 1);
                        }else if(dtb > 0 && dtb < 50){
                            khoa_thongKeHocKi.setSlY(khoa_thongKeHocKi.getSlY() + 1);
                        }else if(dtb >= 50 && dtb < 65){
                            khoa_thongKeHocKi.setSlTB(khoa_thongKeHocKi.getSlTB() + 1);
                        }else if(dtb >= 65 && dtb < 80){
                            khoa_thongKeHocKi.setSlK(khoa_thongKeHocKi.getSlK() + 1);
                        }else if(dtb >= 80 && dtb < 95){
                            khoa_thongKeHocKi.setSlG(khoa_thongKeHocKi.getSlG() + 1);
                        }else{
                            khoa_thongKeHocKi.setSlXS(khoa_thongKeHocKi.getSlXS() + 1);
                        }
                    }
                    else{
                        khoa_thongKeHocKi.setSlKem(khoa_thongKeHocKi.getSlKem() + 1);
                        diem=0;
                    }
                }
                int sl = sinhVienEntityList.size();
                tyleXS = (double)khoa_thongKeHocKi.getSlXS()/sl * 100;
                tyleG = (double)khoa_thongKeHocKi.getSlG()/sl * 100;
                tyleK = (double)khoa_thongKeHocKi.getSlK()/sl * 100;
                tyleTB = (double)khoa_thongKeHocKi.getSlTB()/sl * 100;
                tyleY = (double)khoa_thongKeHocKi.getSlY()/sl * 100;
                tyleKem = (double)khoa_thongKeHocKi.getSlKem()/sl * 100;
                khoa_thongKeHocKi.setTyleXS(tyleXS);
                khoa_thongKeHocKi.setTyleG(tyleG);
                khoa_thongKeHocKi.setTyleK(tyleK);
                khoa_thongKeHocKi.setTyleTB(tyleTB);
                khoa_thongKeHocKi.setTyleY(tyleY);
                khoa_thongKeHocKi.setTyleKem(tyleKem);
                list.add(khoa_thongKeHocKi);
            }
        }
        model.addAttribute("tongdiem",diem);
        model.addAttribute("namhoc",namhoc);
        model.addAttribute("khoa",khoa.getTenKhoa());
        model.addAttribute("listTK",list);
        return "ad_thongkexeploaikhoa";
    }

    public List<KhoaEntity> getKhoaList(){
        return (List<KhoaEntity>)khoaRepository.findAll();
    }

    //Thống kê
    List<KhoaEntity> listKhoa = new ArrayList<>();
    @RequestMapping(value = "/tkkhoa", method = GET)
    public String thongKeKhoa(Model model){
        listKhoa= getKhoaList();
        model.addAttribute("listkhoa",listKhoa);
        return "admin_quanlykhoa";
    }


    List<LopEntity> listLop = new ArrayList<>();
    @RequestMapping(value = "/tklop", method = GET)
    public String thongKeLop(Model model){
        listLop = (List<LopEntity>)lopRepository.findAll();
        model.addAttribute("listlop",listLop);
        return "admin_quanlylop";
    }



    List<NganhHocEntity> listNganh = new ArrayList<>();
    @RequestMapping(value = "/tknganh", method = GET)
    public String thongKeNganh(Model model){
        listNganh = (List<NganhHocEntity>)nganhHocRepository.findAll();
        model.addAttribute("listnganh",listNganh);
        return "admin_quanlynganh";
    }


    List<SinhVienEntity> listSV = new ArrayList<>();
    @RequestMapping(value = "/tksoluongsv", method = GET)
    public String thongKeSV(Model model){
        listSV = (List<SinhVienEntity>)sinhVienRepository.findAll();
        model.addAttribute("listsv",listSV);
        return "admin_quanlysv";
    }


    List<GiaoVienEntity> listGV = new ArrayList<>();
    @RequestMapping(value = "/tksoluonggv", method = GET)
    public String thongKeGV(Model model){
        listGV= (List<GiaoVienEntity>)giangVienRepository.findAll();
        model.addAttribute("listgv",listGV);
        return "admin_quanlygv";
    }

    List<NguoiDungEntity> listND = new ArrayList<>();
    @RequestMapping(value = "/tktaikhoan", method = GET)
    public String thongKeTK(Model model){
        listND= (List<NguoiDungEntity>)nguoiDungRepository.findAll();
        model.addAttribute("listtk",listND);
        return "admin_quanlytk";
    }


    //Tìm kiếm

    @RequestMapping(value = "/getStudent", method = RequestMethod.GET)
    @ResponseBody
    public List<Timkiem> getSV(@RequestParam String studentName) {
        List<Timkiem> result = new ArrayList<>();
        for(SinhVienEntity st : listSV) {
            if (st.getTenSV().contains(studentName)){
                Timkiem sv = new Timkiem();
                sv.setName(st.getTenSV());
                result.add(sv);
            }

        }
        return result;

    }

    @RequestMapping(value = "/getTeacher", method = RequestMethod.GET)
    @ResponseBody
    public List<Timkiem> getGV(@RequestParam String teacherName) {
        List<Timkiem> result = new ArrayList<>();
        for(GiaoVienEntity st : listGV) {
            if (st.getTenGV().contains(teacherName)){
                Timkiem sv = new Timkiem();
                sv.setName(st.getTenGV());
                result.add(sv);
            }
        }
        return result;

    }

    @RequestMapping(value = "/getClass", method = RequestMethod.GET)
    @ResponseBody
    public List<Timkiem> getClass(@RequestParam String className) {
        List<Timkiem> result = new ArrayList<>();
        for(LopEntity st : listLop) {
            if (st.getTenLop().contains(className)){
                Timkiem sv = new Timkiem();
                sv.setName(st.getTenLop());
                result.add(sv);
            }
        }
        return result;

    }

    @RequestMapping(value = "/getNganh", method = RequestMethod.GET)
    @ResponseBody
    public List<Timkiem> getNganh(@RequestParam String nganhName) {
        List<Timkiem> result = new ArrayList<>();
        for(NganhHocEntity st : listNganh) {
            if (st.getTenNganh().contains(nganhName)){
                Timkiem sv = new Timkiem();
                sv.setName(st.getTenNganh());
                result.add(sv);
            }
        }
        return result;

    }

    @RequestMapping(value = "/getKhoa", method = RequestMethod.GET)
    @ResponseBody
    public List<Timkiem> getKhoa(@RequestParam String khoaName) {
        List<Timkiem> result = new ArrayList<>();
        for(KhoaEntity st : listKhoa) {
            if (st.getTenKhoa().contains(khoaName)){
                Timkiem sv = new Timkiem();
                sv.setName(st.getTenKhoa());
                result.add(sv);
            }
        }
        return result;

    }

    @RequestMapping(value = "/getTK", method = RequestMethod.GET)
    @ResponseBody
    public List<Timkiem> getTK(@RequestParam String tkName) {
        List<Timkiem> result = new ArrayList<>();
        for(NguoiDungEntity st : listND) {
            if (st.getTenDangNhap().contains(tkName)){
                Timkiem sv = new Timkiem();
                sv.setName(st.getTenDangNhap());
                result.add(sv);
            }
        }
        return result;

    }

    @RequestMapping(value = "/getTKName", method = RequestMethod.GET)
    public String getTKName(@RequestParam String tkName,Model model) {
        List<NguoiDungEntity> nguoiDungEntities = new ArrayList<>();
        for(NguoiDungEntity st : listND) {
            if (st.getTenDangNhap().contains(tkName)){
               nguoiDungEntities.add(st);
            }
        }
        model.addAttribute("listtk",nguoiDungEntities);
        return "admin_quanlytk";
    }


}
