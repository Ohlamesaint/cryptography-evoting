package ntu.cryptography.eVoting.controller;

import ntu.cryptography.eVoting.dto.TokenDto;
import ntu.cryptography.eVoting.service.MailService;
import ntu.cryptography.eVoting.service.TokenService;
import ntu.cryptography.eVoting.util.AnsiColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static ntu.cryptography.eVoting.util.AnsiColor.ANSI_RESET;

@RestController
@RequestMapping("token")
public class TokenController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private MailService mailService;

    @PostMapping("check-token-exist")
    public Mono<Boolean> isToken (@RequestBody TokenDto tokenDto){
        System.out.println(AnsiColor.ANSI_RED + "[Controller Layer] in isToken" + AnsiColor.ANSI_RESET);
        return this.tokenService.isToken(tokenDto.getToken())
                .doFinally(s -> System.out.println(AnsiColor.ANSI_RED+"[Controller Layer] out of isToken"+ANSI_RESET));
    }

//    @PostMapping("test-mail")
//    public String testMail (@RequestBody TokenDto tokenDto){
//        System.out.println(AnsiColor.ANSI_RED + "[Controller Layer] in testMail" + AnsiColor.ANSI_RESET);
//        return this.mailService.sendEmail(tokenDto);
//    }

}
