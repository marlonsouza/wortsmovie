package souza.marlon.worstmovie.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import souza.marlon.worstmovie.dto.IntervalAward;
import souza.marlon.worstmovie.dto.TopIntervalAwards;
import souza.marlon.worstmovie.model.QIndicated;
import souza.marlon.worstmovie.model.QProducer;

import java.util.Optional;
import java.util.function.Function;

@Repository
@AllArgsConstructor
public class IntervalAwardsQueryImpl implements IntervalAwardsQuery {

    private final EntityManager entityManager;

    @Override
    public TopIntervalAwards execute(long limit){
        var jpaQueryFactory = new JPAQueryFactory(entityManager);
        var indicated = QIndicated.indicated;
        var indicatedLast = new QIndicated("indicatedLast");
        var producer = QProducer.producer;


        var lastIndicated = JPAExpressions.select(indicatedLast.yearAt.max())
                .from(indicatedLast)
                .where(indicatedLast.producers.contains(producer)
                        .and(indicatedLast.isWinner.eq(Boolean.TRUE))
                        .and(indicatedLast.yearAt.lt(indicated.yearAt)));
        var interval = new CaseBuilder().when(lastIndicated.isNull()).then(0L).otherwise(indicated.yearAt.subtract(lastIndicated));

        var baseQuery = jpaQueryFactory
                .select(producer.name, indicated.yearAt,
                        lastIndicated, interval)
                .from(indicated)
                .innerJoin(indicated.producers, producer)
                .where(indicated.isWinner.eq(Boolean.TRUE))
                .groupBy(producer.name, indicated.yearAt)
                .having(interval.gt(0L));


        var maxTopTwo = baseQuery.clone(entityManager).orderBy(interval.desc()).limit(limit)
                .fetch();
        var minTopTwo = baseQuery.clone(entityManager).orderBy(interval.asc()).limit(limit)
                .fetch();

        Function<Tuple, IntervalAward> mapper = tuple -> new IntervalAward(
                tuple.get(producer.name),
                tuple.get(interval),
                Optional.ofNullable(tuple.get(lastIndicated)).orElse(0L),
                tuple.get(indicated.yearAt));

        var maxProducers = maxTopTwo.stream().map(mapper).toList();
        var minProducers = minTopTwo.stream().map(mapper).toList();

        return new TopIntervalAwards(minProducers, maxProducers);
    }

}
