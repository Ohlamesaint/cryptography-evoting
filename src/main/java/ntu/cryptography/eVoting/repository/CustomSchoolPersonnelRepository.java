package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.SchoolPersonnel;
import reactor.core.publisher.Mono;

public interface CustomSchoolPersonnelRepository {

    Mono<SchoolPersonnel> findSchoolPersonnelByUsername(String username);
}
