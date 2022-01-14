package ntu.cryptography.eVoting.controller;


import ntu.cryptography.eVoting.dto.ParticipateDto;
import ntu.cryptography.eVoting.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("participant")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public Flux<ParticipateDto> getParticipantByElectionId(@RequestParam(name = "electionId") String electionId){
        return this.participantService.getParticipateByElectionId(electionId);
    }


    /**
     *     TODO : 可以動態進行查詢學生學號
     *     但目前應該先 hardcoded
     *     其目前是與 create election 連動
     */
    @PostMapping("{electionId}")
    public Flux<ParticipateDto> insertParticipantWithElectionId(@PathVariable String electionId, @RequestBody Flux<ParticipateDto> participateDtoFlux){
        return this.participantService.insertParticipateWithElectionId(electionId, participateDtoFlux);
    }
}
