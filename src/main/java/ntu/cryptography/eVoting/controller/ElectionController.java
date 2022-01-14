package ntu.cryptography.eVoting.controller;

import ntu.cryptography.eVoting.dto.ElectionDto;
import ntu.cryptography.eVoting.dto.ParticipateDto;
import ntu.cryptography.eVoting.service.ElectionService;
import ntu.cryptography.eVoting.service.ParticipantService;
import ntu.cryptography.eVoting.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("election")
public class ElectionController {

    @Autowired
    private ElectionService service;
    @Autowired
    private VoteService voteService;
    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public Flux<ElectionDto> getAll(){
        return this.service.getAll();
    }

    @GetMapping("not-start-yet")
    public Flux<ElectionDto> getNotStartYet(){
        return this.service.getNotStartYet();
    }
    @GetMapping("ongoing")
    public Flux<ElectionDto> getOnGoing(){
        return this.service.getOnGoing();
    }
    @GetMapping("ended")
    public Flux<ElectionDto> getEnded(){
        return this.service.getEnded();
    }

    @GetMapping("election-result")
    public Flux<ParticipateDto> getResult(@RequestParam("electionId") String electionId){
        return this.participantService.getParticipateByElectionId(electionId)
                .flatMap(p -> {
                    return this.voteService.getVoteAmountOfElectionAndCandidate(electionId, p.getCandidateNumber())
                            .handle((i, synchronousSink) -> {
                                System.out.println(i);
                                p.setResult(i);
                            }).thenReturn(p);
                });
    }


}
