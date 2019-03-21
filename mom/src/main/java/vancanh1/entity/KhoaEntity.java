package vancanh1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "khoa")
public class KhoaEntity {
    @Id
    @Column(name = "maKhoa")
    private String maKhoa;

    @Column(name = "tenKhoa")
    private String tenKhoa;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "khoa")
    private List<NganhHocEntity> nganhHocEntities;

    public KhoaEntity() {
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public List<NganhHocEntity> getNganhHocEntities() {
        return nganhHocEntities;
    }

    public void setNganhHocEntities(List<NganhHocEntity> nganhHocEntities) {
        this.nganhHocEntities = nganhHocEntities;
    }
}
