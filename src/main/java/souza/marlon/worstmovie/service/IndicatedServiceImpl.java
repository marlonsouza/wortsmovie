package souza.marlon.worstmovie.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import souza.marlon.worstmovie.model.Indicated;
import souza.marlon.worstmovie.repository.IndicatedRepository;

@Service
@AllArgsConstructor
public class IndicatedServiceImpl implements CrudService<Indicated> {

    private final IndicatedRepository indicatedRepository;

    @Override
    public Indicated save(Indicated toSave) {
        return indicatedRepository.save(toSave);
    }
}
