package vancanh1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "namHoc")
public class NamHocEntity {
    @Id
    @Column(name = "maNH")
    private String maNH;

    @Column(name = "tenNH")
    private String tenNH;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "namHoc")
    private List<HocKiEntity> hocKiEntityList;

    public NamHocEntity() {
    }

    public String getMaNH() {
        return maNH;
    }

    public void setMaNH(String maNH) {
        this.maNH = maNH;
    }

    public String getTenNH() {
        return tenNH;
    }

    public void setTenNH(String tenNH) {
        this.tenNH = tenNH;
    }

    public List<HocKiEntity> getHocKiEntityList() {
        return hocKiEntityList;
    }

    public void setHocKiEntityList(List<HocKiEntity> hocKiEntityList) {
        this.hocKiEntityList = hocKiEntityList;
    }
}
