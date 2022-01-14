package ntu.cryptography.eVoting.controller;

import ntu.cryptography.eVoting.dto.CryptoDto;
import ntu.cryptography.eVoting.dto.VoteDto;
import ntu.cryptography.eVoting.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    // TODO decryption
    @PostMapping("student-vote")
    public Mono<VoteDto> studentVote(@RequestBody Mono<CryptoDto> cryptoDtoMono){
//        return this.voteService.insert(voteDtoMono);
        return cryptoDtoMono.map(c -> {
            System.out.println(c.toString());
            return c;
        }).map(c -> new VoteDto()).doOnError(e -> System.out.println("Error " + e.getMessage()));
    }
}
