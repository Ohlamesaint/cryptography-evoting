package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Participate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ParticipateRepository extends ReactiveMongoRepository<Participate, String>, CustomParticipateRepository {
}
