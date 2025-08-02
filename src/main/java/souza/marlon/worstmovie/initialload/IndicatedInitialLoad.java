package souza.marlon.worstmovie.initialload;

import org.apache.coyote.BadRequestException;

public interface IndicatedInitialLoad {
    void process(IndicatedData indicatedData) throws BadRequestException;
}
