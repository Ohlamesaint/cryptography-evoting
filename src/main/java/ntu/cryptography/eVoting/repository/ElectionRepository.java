package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Election;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends ReactiveMongoRepository<Election, String>, CustomElectionRepository {
}
