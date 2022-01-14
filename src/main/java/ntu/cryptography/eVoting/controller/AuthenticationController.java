package ntu.cryptography.eVoting.controller;

import lombok.AllArgsConstructor;
import ntu.cryptography.eVoting.Authentication.AuthRequest;
import ntu.cryptography.eVoting.Authentication.AuthResponse;
import ntu.cryptography.eVoting.Authentication.JWTUtil;
import ntu.cryptography.eVoting.Authentication.PBKDF2Encoder;
import ntu.cryptography.eVoting.service.SchoolPersonnelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RestController
public class AuthenticationController {

    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder pbkdf2Encoder;
    private final SchoolPersonnelService schoolPersonnelService;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest authRequest) {

        return schoolPersonnelService.getSchoolPersonnelByUsername(authRequest.getUsername())
                .filter(userDetails -> pbkdf2Encoder.encode(authRequest.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }
}
