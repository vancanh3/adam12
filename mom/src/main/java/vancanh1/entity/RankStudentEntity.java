package vancanh1.entity;

import javax.persistence.*;

@Entity
@Table(name = "xeploai")
public class RankStudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "sinhvienId")
    private SinhVienEntity sinhVien;

    @ManyToOne
    @JoinColumn(name = "hockiId")
    private HocKiEntity hocKi;

    @Column(name = "tongdiem")
    private int tongDiem;

    @Column(name = "xeploai")
    private String xepLoai;

    public RankStudentEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public SinhVienEntity getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVienEntity sinhVien) {
        this.sinhVien = sinhVien;
    }

    public HocKiEntity getHocKi() {
        return hocKi;
    }

    public void setHocKi(HocKiEntity hocKi) {
        this.hocKi = hocKi;
    }

    public int getTongDiem() {
        return tongDiem;
    }

    public void setTongDiem(int tongDiem) {
        this.tongDiem = tongDiem;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }
}
