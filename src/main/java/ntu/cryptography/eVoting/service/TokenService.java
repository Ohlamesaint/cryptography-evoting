package ntu.cryptography.eVoting.service;

import ntu.cryptography.eVoting.dto.StudentDto;
import ntu.cryptography.eVoting.dto.TokenDto;
import ntu.cryptography.eVoting.repository.TokenRepository;
import ntu.cryptography.eVoting.util.AnsiColor;
import ntu.cryptography.eVoting.util.EntityDtoUtil;
import ntu.cryptography.eVoting.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Mono<Boolean> isToken(String token){
        System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] in isToken"+AnsiColor.ANSI_RESET);
        return this.tokenRepository.isToken(token);
    }

    public Mono<TokenDto> generateToken(Mono<StudentDto> studentDto){
        System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] in generateToken"+AnsiColor.ANSI_RESET);

        // TODO import the hash function
        /**
         * @param Mono<StudentDto>
         * @return TokenDto
         */
        return studentDto
                .map(StudentDto::getStudentId)
                .map(HashUtil::getTokenSHA256)
                .map(s -> {
                    TokenDto dto = new TokenDto();
                    dto.setToken(s);
                    return dto;
                })
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.tokenRepository::insertToken)
                .map(EntityDtoUtil::toDto)
                .doOnError(e -> System.out.println(e.getMessage()))
                .doAfterTerminate(() -> System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] out of generateToken"+AnsiColor.ANSI_RESET));
    }
}
