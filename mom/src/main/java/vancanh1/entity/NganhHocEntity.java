package vancanh1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "nganhhoc")
public class NganhHocEntity {
    @Id
    @Column(name = "manganh")
    private String maNganh;

    @Column(name = "tennganh")
    private String tenNganh;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "nganh")
    private List<LopEntity> lopEntities;

    @ManyToOne
    @JoinColumn(name = "makhoa")
    private KhoaEntity khoa;

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public List<LopEntity> getLopEntities() {
        return lopEntities;
    }

    public void setLopEntities(List<LopEntity> lopEntities) {
        this.lopEntities = lopEntities;
    }

    public KhoaEntity getKhoa() {
        return khoa;
    }

    public void setKhoa(KhoaEntity khoa) {
        this.khoa = khoa;
    }
}
