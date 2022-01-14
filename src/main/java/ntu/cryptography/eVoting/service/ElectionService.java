package ntu.cryptography.eVoting.service;

import ntu.cryptography.eVoting.dto.ElectionDto;
import ntu.cryptography.eVoting.dto.ParticipateDto;
import ntu.cryptography.eVoting.repository.ElectionRepository;
import ntu.cryptography.eVoting.util.AnsiColor;
import ntu.cryptography.eVoting.util.DateTimeOperator;
import ntu.cryptography.eVoting.util.EntityDtoUtil;
import ntu.cryptography.eVoting.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.util.List;
import java.util.Timer;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    public Flux<ElectionDto> getAll(){
        return this.electionRepository.findAll().map(EntityDtoUtil::toDto);
    }

    public Mono<ElectionDto> getElectionById(String electionId){
        return this.electionRepository.findById(electionId)
                .map(EntityDtoUtil::toDto);
    }

    public Flux<ElectionDto> getNotStartYet(){
        return this.electionRepository.getNotStartYet()
                .map(EntityDtoUtil::toDto);
    }
    public Flux<ElectionDto> getOnGoing(){
        return this.electionRepository.getOngoing()
                .map(e -> {
                    System.out.println(e.toString());
                    return e;
                })
                .map(EntityDtoUtil::toDto)
                .doOnError(e -> System.out.println("Error " + e.getMessage()));
    }
    public Flux<ElectionDto> getEnded(){
        return this.electionRepository.getEnded()
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ElectionDto> insert(Mono<ElectionDto> electionDto){
        System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] in election insert"+AnsiColor.ANSI_RESET);
        return electionDto
                .map(s -> {
                    try {
                        // key generation
                        List<String> keyPair = RSAUtil.keyGen();
                        keyPair.forEach(System.out::println);
                        s.setPrivateKey(keyPair.get(0));
                        s.setPublicKey(keyPair.get(1));
                        s.setStartTime(DateTimeOperator.StringToIsoDate(s.getStartTime().toString()));
                        s.setEndTime(DateTimeOperator.StringToIsoDate(s.getEndTime().toString()));
                        return s;
                    } catch (NoSuchAlgorithmException e) {
                        return Mono.just("Error " + e.getMessage());
                    }
                })
                .map(s -> EntityDtoUtil.toEntity((ElectionDto) s))
                .flatMap(this.electionRepository::insert)
                .map(EntityDtoUtil::toDto)
                .doOnError(e -> System.out.println(e.getMessage()))
                .doAfterTerminate(() -> System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] out of election insert"+AnsiColor.ANSI_RESET));
    }
}
