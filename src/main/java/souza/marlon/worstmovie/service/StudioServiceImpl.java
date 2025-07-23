package souza.marlon.worstmovie.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import souza.marlon.worstmovie.model.Studio;
import souza.marlon.worstmovie.repository.StudioRepository;

@Service
@AllArgsConstructor
public class StudioServiceImpl implements StudioService {

    private final StudioRepository studioRepository;

    @Override
    public Studio save(Studio toSave){
        return studioRepository.save(toSave);
    }

    @Override
    public Studio findByNameOrNew(String name) {
        return studioRepository.findByName(name).orElseGet(() -> Studio.of(name));
    }
}
