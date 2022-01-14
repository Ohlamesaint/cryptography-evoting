package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.Date;

public class CustomElectionRepositoryImpl implements CustomElectionRepository {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Election> getNotStartYet() {
        Query query = new Query(Criteria.where("startTime").gte(Date.from(Instant.now())));

        return mongoTemplate.find(query, Election.class);
    }

    @Override
    public Flux<Election> getOngoing() {
        Query query = new Query(Criteria.where("startTime").lt(Date.from(Instant.now()))
                                        .and("endTime").gte(Date.from(Instant.now())));

        return mongoTemplate.find(query, Election.class);
    }

    @Override
    public Flux<Election> getEnded() {
        Query query = new Query(Criteria.where("endTime").lt(Date.from(Instant.now())));

        return mongoTemplate.find(query, Election.class);
    }
}
