package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomVoteRepository {

    Mono<Long> getVoteOfElectionAndCandidate(String electionId, int participantId);


}
