package vancanh1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hocKy")
public class HocKiEntity {
    @Id
    @Column(name = "maHK")
    private String maHK;

    @Column(name = "tenHK")
    private String tenHK;

    @ManyToOne
    @JoinColumn(name = "namHocId")
    private NamHocEntity namHoc;

    @OneToOne(mappedBy = "hocKi")
    private BangDanhGiaEntity bangDanhGia;

    @OneToMany(mappedBy = "hocKi")
    private List<RankStudentEntity> rankStudentEntities;

    public List<RankStudentEntity> getRankStudentEntities() {
        return rankStudentEntities;
    }

    public void setRankStudentEntities(List<RankStudentEntity> rankStudentEntities) {
        this.rankStudentEntities = rankStudentEntities;
    }

    public HocKiEntity() {
    }

    public void setMaHK(String maHK) {
        this.maHK = maHK;
    }

    public String getMaHK() {
        return maHK;
    }

    public String getTenHK() {
        return tenHK;
    }

    public void setTenHK(String tenHK) {
        this.tenHK = tenHK;
    }

    public NamHocEntity getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(NamHocEntity namHoc) {
        this.namHoc = namHoc;
    }

    public BangDanhGiaEntity getBangDanhGia() {
        return bangDanhGia;
    }

    public void setBangDanhGia(BangDanhGiaEntity bangDanhGia) {
        this.bangDanhGia = bangDanhGia;
    }
}
