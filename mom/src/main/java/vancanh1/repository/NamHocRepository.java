package vancanh1.repository;

import org.springframework.data.repository.CrudRepository;
import vancanh1.entity.NamHocEntity;

public interface NamHocRepository extends CrudRepository<NamHocEntity,Integer> {
    NamHocEntity findByTenNH(String tenNH);
}
