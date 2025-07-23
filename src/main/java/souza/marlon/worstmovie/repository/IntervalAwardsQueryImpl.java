package souza.marlon.worstmovie.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import souza.marlon.worstmovie.dto.IntervalAward;
import souza.marlon.worstmovie.dto.TopIntervalAwards;
import souza.marlon.worstmovie.model.QIndicated;

import java.util.function.Function;

@Repository
@AllArgsConstructor
public class IntervalAwardsQueryImpl implements IntervalAwardsQuery {

    private final EntityManager entityManager;

    @Override
    public TopIntervalAwards execute(long limit){
        var jpaQueryFactory = new JPAQueryFactory(entityManager);
        var indicated = QIndicated.indicated;

        var yearMax = indicated.yearAt.max();
        var yearMin = indicated.yearAt.min();
        var interval = yearMax.subtract(yearMin);

        var baseQuery = jpaQueryFactory
                .select(indicated.producer.name, yearMin, yearMax, interval)
                .from(indicated)
                .groupBy(indicated.producer.name);

        var maxTopTwo = baseQuery.clone(entityManager).orderBy(interval.desc()).limit(limit).fetch();
        var minTopTwo = baseQuery.clone(entityManager).orderBy(interval.asc()).limit(limit).fetch();

        Function<Tuple, IntervalAward> mapper = tuple -> new IntervalAward(
                tuple.get(indicated.producer.name),
                tuple.get(interval),
                tuple.get(yearMin),
                tuple.get(yearMax));

        var maxProducers = maxTopTwo.stream().map(mapper).toList();
        var minProducers = minTopTwo.stream().map(mapper).toList();

        return new TopIntervalAwards(minProducers, maxProducers);
    }

}
