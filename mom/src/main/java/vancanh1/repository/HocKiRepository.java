package vancanh1.repository;

import org.springframework.data.repository.CrudRepository;
import vancanh1.entity.HocKiEntity;
import vancanh1.entity.NamHocEntity;

import java.util.List;

public interface HocKiRepository extends CrudRepository<HocKiEntity,Integer> {
    List<HocKiEntity> findByNamHoc(NamHocEntity nh);
    HocKiEntity findByTenHK(String tenHk);
}
