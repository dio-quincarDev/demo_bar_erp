package pa.com.erpbar.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleEntityRepository extends JpaRepository <RoleEntity, Long>{

    Optional<RoleEntity> findByName(String name);

}
