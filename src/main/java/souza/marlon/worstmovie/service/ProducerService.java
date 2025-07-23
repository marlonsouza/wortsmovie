package souza.marlon.worstmovie.service;

import org.apache.coyote.BadRequestException;
import souza.marlon.worstmovie.model.Producer;

public interface ProducerService extends CrudService<Producer>{

    Producer findByNameOrNew(String name) throws BadRequestException;
}
