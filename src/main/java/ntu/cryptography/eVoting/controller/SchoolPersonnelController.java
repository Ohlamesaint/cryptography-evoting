package ntu.cryptography.eVoting.controller;

import ntu.cryptography.eVoting.Authentication.PBKDF2Encoder;
import ntu.cryptography.eVoting.dto.ElectionDto;
import ntu.cryptography.eVoting.dto.SchoolPersonnelDto;
import ntu.cryptography.eVoting.service.ElectionService;
import ntu.cryptography.eVoting.service.SchoolPersonnelService;
import ntu.cryptography.eVoting.util.AnsiColor;
import ntu.cryptography.eVoting.util.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static ntu.cryptography.eVoting.util.AnsiColor.ANSI_RESET;

@RestController
@RequestMapping("schoolPersonnel")
public class SchoolPersonnelController {

    @Autowired
    private SchoolPersonnelService service;
    @Autowired
    private PBKDF2Encoder pbkdf2Encoder;
    @Autowired
    private ElectionService electionService;

    @GetMapping
    public Flux<SchoolPersonnelDto> getAll(){
        System.out.println(AnsiColor.ANSI_RED+"[Controller Layer] in get all school personnel"+ANSI_RESET);
        return this.service.getAll()
                .doFinally(s -> System.out.println(AnsiColor.ANSI_RED+"[Controller Layer] out of get all school personnel"+ANSI_RESET));
    }

    @PostMapping
    public Mono<SchoolPersonnelDto> insertSchoolPersonnel(@RequestBody Mono<SchoolPersonnelDto> dto){
        System.out.println(AnsiColor.ANSI_RED+"[Controller Layer] in insert school personnel"+ANSI_RESET);
        return this.service.insert(dto.map(s -> {
            s.setPassword(pbkdf2Encoder.encode(s.getPassword()));
            return s;}
        )).doFinally(s -> System.out.println(AnsiColor.ANSI_RED+"[Controller Layer] out of insert school personnel"+ANSI_RESET));
    }

    @PostMapping("create-election")
    public Mono<ElectionDto> createElectionAndSetCandidate (@RequestBody Mono<ElectionDto> dto){
        System.out.println(AnsiColor.ANSI_RED+"[Controller Layer] in createElection"+ANSI_RESET);
        return this.electionService.insert(dto);
    }
}
