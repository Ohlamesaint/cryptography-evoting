package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Vote;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends ReactiveMongoRepository<Vote, String>, CustomVoteRepository {
}
