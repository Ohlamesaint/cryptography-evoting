package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Token;
import reactor.core.publisher.Mono;

public interface CustomTokenRepository {
    Mono<Boolean> isToken(String token);

    Mono<Token> insertToken(Token token);
}
