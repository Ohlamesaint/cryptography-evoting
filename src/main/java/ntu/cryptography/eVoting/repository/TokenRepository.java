package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Token;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends ReactiveMongoRepository<Token, String>, CustomTokenRepository{
}
