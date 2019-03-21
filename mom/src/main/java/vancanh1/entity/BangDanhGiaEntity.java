package vancanh1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bangDanhGia")
public class BangDanhGiaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "hockiId")
    private HocKiEntity hocKi;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "bangDanhGia")
    private List<TieuChiEntity> tieuChiEntityList;

    @OneToMany(mappedBy = "bangDanhGia")
    private List<DanhGiaSVEntity> danhGiaSVEntities;

    @OneToMany(mappedBy = "bangDanhGia")
    private List<DiemTieuChiSinhVienEntity> diemTieuChiSinhVienEntities;

    public List<DiemTieuChiSinhVienEntity> getDiemTieuChiSinhVienEntities() {
        return diemTieuChiSinhVienEntities;
    }

    public void setDiemTieuChiSinhVienEntities(List<DiemTieuChiSinhVienEntity> diemTieuChiSinhVienEntities) {
        this.diemTieuChiSinhVienEntities = diemTieuChiSinhVienEntities;
    }

    public BangDanhGiaEntity() {
    }


    public List<DanhGiaSVEntity> getDanhGiaSVEntities() {
        return danhGiaSVEntities;
    }

    public void setDanhGiaSVEntities(List<DanhGiaSVEntity> danhGiaSVEntities) {
        this.danhGiaSVEntities = danhGiaSVEntities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HocKiEntity getHocKi() {
        return hocKi;
    }

    public void setHocKi(HocKiEntity hocKi) {
        this.hocKi = hocKi;
    }

    public List<TieuChiEntity> getTieuChiEntityList() {
        return tieuChiEntityList;
    }

    public void setTieuChiEntityList(List<TieuChiEntity> tieuChiEntityList) {
        this.tieuChiEntityList = tieuChiEntityList;
    }


}
