package souza.marlon.worstmovie.repository;

import souza.marlon.worstmovie.dto.TopIntervalAwards;

public interface IntervalAwardsQuery {
    TopIntervalAwards execute(long limit);
}
