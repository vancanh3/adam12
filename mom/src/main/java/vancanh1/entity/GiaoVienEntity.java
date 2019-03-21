package vancanh1.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "giaovien")
public class GiaoVienEntity {
    @Id
    @Column(name = "maGV")
    private String maGV;

    @Column(name = "tenGV")
    private String tenGV;

    @Column(name = "diachi")
    private String diachi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "ngaysinh")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date ngaysinh;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "giaoVien")
    private List<LopEntity> lopEntityList;


    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public List<LopEntity> getLopEntityList() {
        return lopEntityList;
    }

    public void setLopEntityList(List<LopEntity> lopEntityList) {
        this.lopEntityList = lopEntityList;
    }
}
