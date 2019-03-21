package vancanh1.entity;

public class ThongKeCaNam {
    private RankStudentEntity rankStudentEntity;
    private int diemHKI;
    private int diemHKII;
    private String xeploaiHKI;
    private String xeploaiHKII;
    private String xeploaiCN;
    private double diemCN;
    public int getDiemHKI() {
        return diemHKI;
    }
    private SinhVienEntity sinhVien;

    public SinhVienEntity getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVienEntity sinhVien) {
        this.sinhVien = sinhVien;
    }

    public void setDiemHKI(int diemHKI) {
        this.diemHKI = diemHKI;
    }

    public int getDiemHKII() {
        return diemHKII;
    }

    public void setDiemHKII(int diemHKII) {
        this.diemHKII = diemHKII;
    }

    public String getXeploaiHKI() {
        return xeploaiHKI;
    }

    public void setXeploaiHKI(String xeploaiHKI) {
        this.xeploaiHKI = xeploaiHKI;
    }

    public String getXeploaiHKII() {
        return xeploaiHKII;
    }

    public void setXeploaiHKII(String xeploaiHKII) {
        this.xeploaiHKII = xeploaiHKII;
    }

    public String getXeploaiCN() {
        return xeploaiCN;
    }

    public void setXeploaiCN(String xeploaiCN) {
        this.xeploaiCN = xeploaiCN;
    }

    public double getDiemCN() {
        return diemCN;
    }

    public void setDiemCN(double diemCN) {
        this.diemCN = diemCN;
    }

    public ThongKeCaNam() {
    }



    public RankStudentEntity getRankStudentEntity() {
        return rankStudentEntity;
    }

    public void setRankStudentEntity(RankStudentEntity rankStudentEntity) {
        this.rankStudentEntity = rankStudentEntity;
    }
}

