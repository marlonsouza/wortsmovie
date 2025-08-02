package souza.marlon.worstmovie.service;

import souza.marlon.worstmovie.model.Producer;

public interface ProducerService extends CrudService<Producer>{

    Producer findByNameOrNew(String name);
}
