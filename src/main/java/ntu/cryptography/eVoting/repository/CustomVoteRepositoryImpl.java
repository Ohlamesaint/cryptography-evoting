package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

public class CustomVoteRepositoryImpl implements CustomVoteRepository{

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Long> getVoteOfElectionAndCandidate(String electionId, int candidateNumber) {
        Query query = new Query(Criteria.where("electionId").is(electionId)
                .and("candidateNumber").is(candidateNumber));
        Mono<Long> temp = mongoTemplate.count(query, Vote.class);

        return temp;
    }
}
