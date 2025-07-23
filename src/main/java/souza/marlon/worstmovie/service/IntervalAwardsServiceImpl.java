package souza.marlon.worstmovie.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import souza.marlon.worstmovie.dto.TopIntervalAwards;
import souza.marlon.worstmovie.repository.IntervalAwardsQuery;

import java.util.Optional;

@AllArgsConstructor
@Service
public class IntervalAwardsServiceImpl implements IntervalAwardsService {

    private static final long LIMIT_DEFAULT = 2L;

    private final IntervalAwardsQuery query;

    @Override
    public TopIntervalAwards listTopIntervalAwarsds(Long limit){
        return query.execute(Optional.ofNullable(limit).orElse(LIMIT_DEFAULT));
    }
}
