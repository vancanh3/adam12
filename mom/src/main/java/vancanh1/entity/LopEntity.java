package vancanh1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lop")
public class LopEntity {
    @Id
    @Column(name = "maLop")
    private String maLop;

    @Column(name = "tenLop")
    private String tenLop;

    @ManyToOne
    @JoinColumn(name = "manganh")
    private NganhHocEntity nganh;

    @ManyToOne
    @JoinColumn(name = "giaovienId")
    private GiaoVienEntity giaoVien;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "lop")
    private List<SinhVienEntity> sinhVienEntities;

    public LopEntity() {
    }

    public NganhHocEntity getNganh() {
        return nganh;
    }

    public void setNganh(NganhHocEntity nganh) {
        this.nganh = nganh;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }


    public GiaoVienEntity getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(GiaoVienEntity giaoVien) {
        this.giaoVien = giaoVien;
    }

    public List<SinhVienEntity> getSinhVienEntities() {
        return sinhVienEntities;
    }

    public void setSinhVienEntities(List<SinhVienEntity> sinhVienEntities) {
        this.sinhVienEntities = sinhVienEntities;
    }
}
