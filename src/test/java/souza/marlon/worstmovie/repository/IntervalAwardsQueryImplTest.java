package souza.marlon.worstmovie.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class IntervalAwardsQueryImplTest {

    @Autowired
    private IntervalAwardsQueryImpl query;

    @Test
    void testExecuteQuerySucess() {
        int limit = 3;
        var topIntervalAwards = query.execute(limit);

        var maxList = topIntervalAwards.max();
        var minList = topIntervalAwards.min();

        assertEquals(limit, maxList.size());
        maxList.forEach(max -> {
            assertNotNull(max.producer());
            assertNotNull(max.interval());
            assertNotNull(max.followingWin());
            assertNotNull(max.previousWin());
        });

        assertEquals(limit, minList.size());
        minList.forEach(min -> {
            assertNotNull(min.producer());
            assertNotNull(min.interval());
            assertNotNull(min.followingWin());
            assertNotNull(min.previousWin());
        });
    }
}