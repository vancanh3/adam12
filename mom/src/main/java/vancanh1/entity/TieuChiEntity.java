package vancanh1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tieuchi")
public class TieuChiEntity {
    @Id
    @Column(name = "maTC")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maTC;

    @Column(name = "tenTC")
    private String tenTC;

    @Column(name = "diem")
    private int diem;

    @ManyToOne
    @JoinColumn(name = "bangDanhGiaId")
    private BangDanhGiaEntity bangDanhGia;

    public List<DiemTieuChiSinhVienEntity> getDiemTieuChiSinhVienEntities() {
        return diemTieuChiSinhVienEntities;
    }

    public void setDiemTieuChiSinhVienEntities(List<DiemTieuChiSinhVienEntity> diemTieuChiSinhVienEntities) {
        this.diemTieuChiSinhVienEntities = diemTieuChiSinhVienEntities;
    }

    @OneToMany(mappedBy = "tieuChi")
    private List<DiemTieuChiSinhVienEntity> diemTieuChiSinhVienEntities;

    public TieuChiEntity() {
    }

    public int getMaTC() {
        return maTC;
    }

    public void setMaTC(int maTC) {
        this.maTC = maTC;
    }

    public BangDanhGiaEntity getBangDanhGia() {
        return bangDanhGia;
    }

    public void setBangDanhGia(BangDanhGiaEntity bangDanhGia) {
        this.bangDanhGia = bangDanhGia;
    }

    public String getTenTC() {
        return tenTC;
    }

    public void setTenTC(String tenTC) {
        this.tenTC = tenTC;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }


}
