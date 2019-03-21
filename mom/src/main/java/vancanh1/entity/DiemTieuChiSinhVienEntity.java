package vancanh1.entity;

import javax.persistence.*;

@Entity
@Table(name = "tieuchisinhvien")
public class DiemTieuChiSinhVienEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "tieuchiid")
    private TieuChiEntity tieuChi;

    @ManyToOne
    @JoinColumn(name = "sinhvienid")
    private SinhVienEntity sinhVien;

    @Column(name = "diemtieuchi")
    private int diemTieuChi;

    @ManyToOne
    @JoinColumn(name = "bangDanhGiaId")
    private BangDanhGiaEntity bangDanhGia;

    public DiemTieuChiSinhVienEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TieuChiEntity getTieuChi() {
        return tieuChi;
    }

    public void setTieuChi(TieuChiEntity tieuChi) {
        this.tieuChi = tieuChi;
    }

    public SinhVienEntity getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVienEntity sinhVien) {
        this.sinhVien = sinhVien;
    }

    public int getDiemTieuChi() {
        return diemTieuChi;
    }

    public void setDiemTieuChi(int diemTieuChi) {
        this.diemTieuChi = diemTieuChi;
    }

    public BangDanhGiaEntity getBangDanhGia() {
        return bangDanhGia;
    }

    public void setBangDanhGia(BangDanhGiaEntity bangDanhGia) {
        this.bangDanhGia = bangDanhGia;
    }
}
