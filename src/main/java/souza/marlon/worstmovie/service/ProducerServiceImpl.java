package souza.marlon.worstmovie.service;

import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import souza.marlon.worstmovie.model.Producer;
import souza.marlon.worstmovie.repository.ProducerRepository;

@Service
@AllArgsConstructor
public class ProducerServiceImpl implements ProducerService {

    private final ProducerRepository repository;

    @Override
    public Producer save(Producer toSave) {
        return repository.save(toSave);
    }

    @Override
    public Producer findByNameOrNew(String name){
        return repository.findByName(name).orElseGet(() -> Producer.of(name));
    }
}