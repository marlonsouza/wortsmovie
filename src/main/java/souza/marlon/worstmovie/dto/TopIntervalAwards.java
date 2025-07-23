package souza.marlon.worstmovie.dto;

import java.util.Collection;
import java.util.List;

public record TopIntervalAwards(List<IntervalAward> min, List<IntervalAward> max) {
}
