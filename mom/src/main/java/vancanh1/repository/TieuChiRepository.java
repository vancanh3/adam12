package vancanh1.repository;

import org.springframework.data.repository.CrudRepository;
import vancanh1.entity.TieuChiEntity;

import java.util.List;

public interface TieuChiRepository extends CrudRepository<TieuChiEntity,Integer> {
    List<TieuChiEntity> findByBangDanhGiaId(int id);
}
