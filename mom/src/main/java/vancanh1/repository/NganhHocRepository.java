package vancanh1.repository;

import org.springframework.data.repository.CrudRepository;
import vancanh1.entity.KhoaEntity;
import vancanh1.entity.NganhHocEntity;

import java.util.List;

public interface NganhHocRepository extends CrudRepository<NganhHocEntity,String> {
    List<NganhHocEntity> findByKhoa(KhoaEntity khoa);
}
