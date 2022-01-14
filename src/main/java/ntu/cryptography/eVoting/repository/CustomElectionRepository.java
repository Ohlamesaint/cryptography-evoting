package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Election;
import reactor.core.publisher.Flux;

public interface CustomElectionRepository {

    Flux<Election> getNotStartYet();

    Flux<Election> getOngoing();

    Flux<Election> getEnded();
}
