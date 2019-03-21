package vancanh1.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sinhvien")
public class SinhVienEntity {
    @Id
    @Column(name = "maSV")
    private String maSV;

    @Column(name = "tenSV")
    private String tenSV;

    @Column(name = "diachi")
    private String diachi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "ngaysinh")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date ngaysinh;


    @Column(name="khoahoc")
    private String khoaHoc;

    @ManyToOne
    @JoinColumn(name = "lopId")
    private LopEntity lop;

    public String getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(String khoaHoc) {
        this.khoaHoc = khoaHoc;
    }
    @OneToMany(mappedBy = "sinhVien")
    private List<DiemTieuChiSinhVienEntity> diemTieuChiSinhVienEntities;

    public List<DiemTieuChiSinhVienEntity> getDiemTieuChiSinhVienEntities() {
        return diemTieuChiSinhVienEntities;
    }

    public void setDiemTieuChiSinhVienEntities(List<DiemTieuChiSinhVienEntity> diemTieuChiSinhVienEntities) {
        this.diemTieuChiSinhVienEntities = diemTieuChiSinhVienEntities;
    }

    @OneToMany(mappedBy = "sinhVien")
    private List<DanhGiaSVEntity> danhGiaSVEntities;

    @OneToMany(mappedBy = "sinhVien")
    private List<RankStudentEntity> rankStudentEntities;

    public List<RankStudentEntity> getRankStudentEntities() {
        return rankStudentEntities;
    }

    public void setRankStudentEntities(List<RankStudentEntity> rankStudentEntities) {
        this.rankStudentEntities = rankStudentEntities;
    }

    public SinhVienEntity() {
    }


    public List<DanhGiaSVEntity> getDanhGiaSVEntities() {
        return danhGiaSVEntities;
    }

    public void setDanhGiaSVEntities(List<DanhGiaSVEntity> danhGiaSVEntities) {
        this.danhGiaSVEntities = danhGiaSVEntities;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public LopEntity getLop() {
        return lop;
    }

    public void setLop(LopEntity lop) {
        this.lop = lop;
    }
}
