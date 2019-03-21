package vancanh1.repository;

import org.springframework.data.repository.CrudRepository;
import vancanh1.entity.GiaoVienEntity;
import vancanh1.entity.LopEntity;
import vancanh1.entity.NganhHocEntity;

import java.util.List;

public interface LopRepository extends CrudRepository<LopEntity,String> {
    List<LopEntity> findByGiaoVien(GiaoVienEntity gv);
    List<LopEntity> findByNganh(NganhHocEntity nganhHocEntity);

}
