package ntu.cryptography.eVoting.controller;

import ntu.cryptography.eVoting.dto.StudentDto;
import ntu.cryptography.eVoting.dto.TokenDto;
import ntu.cryptography.eVoting.entity.Election;
import ntu.cryptography.eVoting.service.ElectionService;
import ntu.cryptography.eVoting.service.MailService;
import ntu.cryptography.eVoting.service.StudentService;
import ntu.cryptography.eVoting.service.TokenService;
import ntu.cryptography.eVoting.util.AnsiColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;

import static ntu.cryptography.eVoting.util.AnsiColor.ANSI_RESET;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService service;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ElectionService electionService;
    @Autowired
    private MailService mailService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public Flux<StudentDto> getAllStudent(){
        System.out.println(AnsiColor.ANSI_RED + "[Controller Layer] in get all student" + AnsiColor.ANSI_RESET);
        return this.service.getAllStudent()
                .doFinally(s -> System.out.println(AnsiColor.ANSI_RED+"[Controller Layer] out of get all student"+ANSI_RESET));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Mono<StudentDto> insertStudent(@RequestBody Mono<StudentDto> dto){
        System.out.println(AnsiColor.ANSI_RED + "[Controller Layer] in insert student" + AnsiColor.ANSI_RESET);
        return this.service.insertStudent(dto)
                .doFinally(s -> System.out.println(AnsiColor.ANSI_RED+"[Controller Layer] out of insert student"+ANSI_RESET));
    }

    @GetMapping("get-student-by-id/{studentId}")
    public Mono<StudentDto> getStudentById(@PathVariable String studentId){
        return this.service.getStudentById(studentId);
    }


    // TODO send email functionality
    @PostMapping("student-generate-token-get-mail/{electionId}")
    public Mono<ResponseEntity<Object>> studentGenerateTokenAndGetMail(@RequestBody String studentId, @PathVariable String electionId){
        System.out.println(AnsiColor.ANSI_RED + "[Controller Layer] in studentGenerateTokenAndGetMail" + ANSI_RESET);
        return this.service.isStudentExist(studentId)
                .flatMap(s -> {
                    if(s != null) return this.tokenService.generateToken(Mono.just(s));
                    else return Mono.empty();
                })
                .flatMap(t -> {
                    return this.electionService.getElectionById(electionId)
                            .map(e -> {
                                try {
                                    this.mailService.sendMail(e.getName(), electionId, t.getToken(), studentId);
                                } catch (MessagingException messagingException) {
                                    messagingException.printStackTrace();
                                }
                                return null;
                            }).thenReturn(3);
                })
                .map(m -> ResponseEntity.ok().build())
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()))
                .doFinally(s -> System.out.println(AnsiColor.ANSI_RED+"[Controller Layer] out of student-generate-token-get-mail"+ANSI_RESET));
    }

}


