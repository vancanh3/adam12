package vancanh1.repository;

import org.springframework.data.repository.CrudRepository;
import vancanh1.entity.HocKiEntity;
import vancanh1.entity.LopEntity;
import vancanh1.entity.RankStudentEntity;
import vancanh1.entity.SinhVienEntity;

import java.util.List;

public interface XepLoaiSinhVienRepository extends CrudRepository<RankStudentEntity,Integer> {
    List<RankStudentEntity>  findByXepLoai(String xeploai);
    List<RankStudentEntity> findBySinhVien(SinhVienEntity sinhVienEntity);
    List<RankStudentEntity> findBySinhVienAndHocKi(SinhVienEntity sinhVienEntity, HocKiEntity hocKiEntity);
    List<RankStudentEntity> findByHocKi(HocKiEntity hocKiEntity);
    RankStudentEntity findByHocKiAndSinhVien(HocKiEntity hocKiEntity, SinhVienEntity sv);
}
