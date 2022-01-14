package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.SchoolPersonnel;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SchoolPersonnelRepository extends ReactiveMongoRepository<SchoolPersonnel, String>, CustomSchoolPersonnelRepository {

}
