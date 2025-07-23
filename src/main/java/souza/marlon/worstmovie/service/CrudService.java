package souza.marlon.worstmovie.service;

import souza.marlon.worstmovie.model.Studio;

public interface CrudService<T> {
    T save(T toSave);
}
