package vancanh1.repository;

import org.springframework.data.repository.CrudRepository;
import vancanh1.entity.BangDanhGiaEntity;
import vancanh1.entity.HocKiEntity;

public interface BangDanhGiaRepository extends CrudRepository<BangDanhGiaEntity,Integer> {
    BangDanhGiaEntity findByHocKi(HocKiEntity hk);
}
