package souza.marlon.worstmovie.service;

import souza.marlon.worstmovie.dto.TopIntervalAwards;

public interface IntervalAwardsService {
    TopIntervalAwards listTopIntervalAwarsds(Long limit);
}
