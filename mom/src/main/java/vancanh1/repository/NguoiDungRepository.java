package vancanh1.repository;

import org.springframework.data.repository.CrudRepository;
import vancanh1.entity.NguoiDungEntity;

public interface NguoiDungRepository extends CrudRepository<NguoiDungEntity,Long> {
    NguoiDungEntity findByTenDangNhapAndMatKhau(String tendn, String mk);

}
