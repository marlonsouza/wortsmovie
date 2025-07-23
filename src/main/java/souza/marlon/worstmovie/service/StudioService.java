package souza.marlon.worstmovie.service;

import org.apache.coyote.BadRequestException;
import souza.marlon.worstmovie.model.Studio;

public interface StudioService extends CrudService<Studio> {

    Studio findByNameOrNew(String name) throws BadRequestException;
}
