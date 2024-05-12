package Spring.AsetProject.Repository;



import Spring.AsetProject.Entities.Ranks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RanksRepo extends JpaRepository<Ranks, Long> {
}
