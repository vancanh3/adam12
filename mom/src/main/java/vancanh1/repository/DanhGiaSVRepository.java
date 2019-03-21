package vancanh1.repository;

import org.springframework.data.repository.CrudRepository;
import vancanh1.entity.BangDanhGiaEntity;
import vancanh1.entity.DanhGiaSVEntity;
import vancanh1.entity.SinhVienEntity;

import java.util.List;

public interface DanhGiaSVRepository extends CrudRepository<DanhGiaSVEntity,Integer> {
    List<DanhGiaSVEntity> findBySinhVien(SinhVienEntity sv);
    DanhGiaSVEntity findBySinhVienAndBangDanhGia(SinhVienEntity sv, BangDanhGiaEntity bangDanhGiaEntity);

}
