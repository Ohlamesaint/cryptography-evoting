package ntu.cryptography.eVoting.service;


import ntu.cryptography.eVoting.dto.VoteDto;
import ntu.cryptography.eVoting.entity.Vote;
import ntu.cryptography.eVoting.repository.VoteRepository;
import ntu.cryptography.eVoting.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class VoteService {

    @Autowired
    private VoteRepository repository;

    public Flux<VoteDto> getAll(){
        return this.repository.findAll()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<VoteDto> insert(Mono<VoteDto> vote){
        return vote.map(EntityDtoUtil::toEntity)
                .flatMap(this.repository::insert)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<Long> getVoteAmountOfElectionAndCandidate(String electionId, int candidateNumber){

        return this.repository.getVoteOfElectionAndCandidate(electionId, candidateNumber);
    }

}
