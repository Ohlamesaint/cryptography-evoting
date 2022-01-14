package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Participate;
import reactor.core.publisher.Flux;

public interface CustomParticipateRepository {

    Flux<Participate> getParticipateByElectionId(String electionId);
}
