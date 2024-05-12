package Spring.AsetProject.Repository;


import Spring.AsetProject.Entities.Employers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployersRepo extends JpaRepository<Employers, Long> {

    @Query("SELECT u FROM Employers u WHERE u.name  like %?1% or u.surname like %?1% or  u.position.positionName=?1 or u.department.departmentName=?1 or u.ranks.ranksName=?1")
    List<Employers> findByKey(String key);

}
