package vancanh1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "phanquyen")
public class PhanQuyenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "quyen")
    private String quyen;


    @OneToMany(fetch = FetchType.EAGER,mappedBy = "phanQuyen")
    private List<NguoiDung_PhanQuyenEntity> nguoiDung_phanQuyenEntities;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }


    public List<NguoiDung_PhanQuyenEntity> getNguoiDung_phanQuyenEntities() {
        return nguoiDung_phanQuyenEntities;
    }

    public void setNguoiDung_phanQuyenEntities(List<NguoiDung_PhanQuyenEntity> nguoiDung_phanQuyenEntities) {
        this.nguoiDung_phanQuyenEntities = nguoiDung_phanQuyenEntities;
    }
}
