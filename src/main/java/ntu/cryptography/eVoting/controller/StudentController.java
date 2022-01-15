package ntu.cryptography.eVoting.controller;

import ntu.cryptography.eVoting.dto.StudentDto;
import ntu.cryptography.eVoting.dto.TokenDto;
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

import static ntu.cryptography.eVoting.util.AnsiColor.ANSI_RESET;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService service;
    @Autowired
    private TokenService tokenService;
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
    public Mono<ResponseEntity<TokenDto>> studentGenerateTokenAndGetMail(@RequestBody Mono<String> studentId, @PathVariable String electionId){
        System.out.println(AnsiColor.ANSI_RED + "[Controller Layer] in studentGenerateTokenAndGetMail" + ANSI_RESET);
        return studentId.map(this.service::isStudentExist)
                .flatMap(s -> {
                    if(s != null) return this.tokenService.generateToken(s);
                    else return Mono.empty();
                })
                .map(t -> {
                    this.mailService.sendEmail(t, electionId);
                    return t;
                })
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.badRequest().build()))
                .doFinally(s -> System.out.println(AnsiColor.ANSI_RED+"[Controller Layer] out of student-generate-token-get-mail"+ANSI_RESET));
    }

}


