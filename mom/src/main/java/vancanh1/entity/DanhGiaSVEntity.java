package vancanh1.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="diemDGSV")
public class DanhGiaSVEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "tongdiem")
    private int tongDiem;

    @ManyToOne
    @JoinColumn(name = "sinhvienId")
    private SinhVienEntity sinhVien;

    @ManyToOne
    @JoinColumn(name = "bangDanhGiaId")
    private BangDanhGiaEntity bangDanhGia;

    @Column(name = "ngayDG")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date ngayDG;



    public DanhGiaSVEntity() {
    }

    public Date getNgayDG() {
        return ngayDG;
    }

    public void setNgayDG(Date ngayDG) {
        this.ngayDG = ngayDG;
    }

    public BangDanhGiaEntity getBangDanhGia() {
        return bangDanhGia;
    }

    public void setBangDanhGia(BangDanhGiaEntity bangDanhGia) {
        this.bangDanhGia = bangDanhGia;
    }

    public SinhVienEntity getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVienEntity sinhVien) {
        this.sinhVien = sinhVien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTongDiem() {
        return tongDiem;
    }

    public void setTongDiem(int tongDiem) {
        this.tongDiem = tongDiem;
    }
}
