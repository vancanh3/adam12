package vancanh1.entity;

import javax.persistence.*;

@Entity
@Table(name = "nguoidung_phanquyen")
public class NguoiDung_PhanQuyenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "nguoidung")
    private NguoiDungEntity nguoiDung;

    @ManyToOne
    @JoinColumn(name = "phanquyen")
    private PhanQuyenEntity phanQuyen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NguoiDungEntity getNguoiDung() {
        return nguoiDung;
    }

    public void setNguoiDung(NguoiDungEntity nguoiDung) {
        this.nguoiDung = nguoiDung;
    }

    public PhanQuyenEntity getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(PhanQuyenEntity phanQuyen) {
        this.phanQuyen = phanQuyen;
    }
}
