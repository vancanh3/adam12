package vancanh1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nguoidung")
public class NguoiDungEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tendangnhap")
    private String tenDangNhap;

    @Column(name = "matkhau")
    private String matKhau;

    @Column(name = "tinhtrang")
    private boolean tinhtrang;

    public boolean isTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(boolean tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "nguoiDung")
    private List<NguoiDung_PhanQuyenEntity> nguoiDung_phanQuyenEntities;

    public List<NguoiDung_PhanQuyenEntity> getNguoiDung_phanQuyenEntities() {
        return nguoiDung_phanQuyenEntities;
    }

    public void setNguoiDung_phanQuyenEntities(List<NguoiDung_PhanQuyenEntity> nguoiDung_phanQuyenEntities) {
        this.nguoiDung_phanQuyenEntities = nguoiDung_phanQuyenEntities;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }



}
