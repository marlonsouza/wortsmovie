package souza.marlon.worstmovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import souza.marlon.worstmovie.model.Studio;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudioRepository extends JpaRepository<Studio, UUID> {

    Optional<Studio> findByName(String name);
}
