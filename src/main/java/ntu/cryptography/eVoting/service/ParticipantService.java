package ntu.cryptography.eVoting.service;

import ntu.cryptography.eVoting.dto.ParticipateDto;
import ntu.cryptography.eVoting.repository.ParticipateRepository;
import ntu.cryptography.eVoting.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ParticipantService {

    @Autowired
    private ParticipateRepository repository;

    public Flux<ParticipateDto> getParticipateByElectionId(String electionId){
        return this.repository.getParticipateByElectionId(electionId)
                .map(EntityDtoUtil::toDto);
    }

    public Flux<ParticipateDto> insertParticipateWithElectionId(String electionId, Flux<ParticipateDto> participateDtoFlux){
        return participateDtoFlux
                .map(participateDto -> {
                    System.out.println(electionId);
                    participateDto.setElectionId(electionId);
                    System.out.println(participateDto.toString());
                    return participateDto;
                })
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.repository::insert)
                .onErrorContinue((e, participateDto) -> System.out.println("Error" + e.getMessage()))
                .map(EntityDtoUtil::toDto);
    }
}
