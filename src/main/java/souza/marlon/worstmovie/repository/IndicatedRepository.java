package souza.marlon.worstmovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import souza.marlon.worstmovie.model.Indicated;

import java.util.UUID;

@Repository
public interface IndicatedRepository extends JpaRepository<Indicated, UUID> {
}
