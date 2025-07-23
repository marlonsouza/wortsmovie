package souza.marlon.worstmovie.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import souza.marlon.worstmovie.dto.IntervalAward;
import souza.marlon.worstmovie.dto.TopIntervalAwards;
import souza.marlon.worstmovie.repository.IntervalAwardsQuery;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class IntervalAwardsServiceImplTest {

    @InjectMocks
    private IntervalAwardsServiceImpl intervalAwardsService;

    @Mock
    private IntervalAwardsQuery query;

    @Captor
    private ArgumentCaptor<Long> limitCaptur;

    @Test
    void testWithLimit() {

        var mockInterval = List.of(new IntervalAward("PRODUCER", 10L, 2015L, 2025L));

        Mockito.when(query.execute(anyLong())).thenReturn(new TopIntervalAwards(mockInterval, mockInterval));

        intervalAwardsService.listTopIntervalAwarsds(100L);

        Mockito.verify(query).execute(limitCaptur.capture());

        var limit = limitCaptur.getValue();

        Assertions.assertEquals(100, limit);
    }

    @Test
    void testWithNoLimit() {

        var mockInterval = List.of(new IntervalAward("PRODUCER", 10L, 2015L, 2025L));

        Mockito.when(query.execute(anyLong())).thenReturn(new TopIntervalAwards(mockInterval, mockInterval));

        intervalAwardsService.listTopIntervalAwarsds(null);

        Mockito.verify(query).execute(limitCaptur.capture());

        var limit = limitCaptur.getValue();

        Assertions.assertEquals(2, limit);
    }
}