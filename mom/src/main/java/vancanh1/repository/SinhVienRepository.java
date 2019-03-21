package vancanh1.repository;

import org.springframework.data.repository.CrudRepository;
import vancanh1.entity.LopEntity;
import vancanh1.entity.SinhVienEntity;

import java.util.List;

public interface SinhVienRepository extends CrudRepository<SinhVienEntity,String> {
    List<SinhVienEntity>  findByLop(LopEntity lop);
    SinhVienEntity findByMaSV(String maSV);
}
