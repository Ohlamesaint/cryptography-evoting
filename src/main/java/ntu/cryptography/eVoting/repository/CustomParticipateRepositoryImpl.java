package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Participate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;

public class CustomParticipateRepositoryImpl implements CustomParticipateRepository{

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Flux<Participate> getParticipateByElectionId(String electionId) {
        Query query = new Query(Criteria.where("electionId").is(electionId));

        return reactiveMongoTemplate.find(query, Participate.class);
    }
}
