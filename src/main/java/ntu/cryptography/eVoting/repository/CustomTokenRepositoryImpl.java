package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;


public class CustomTokenRepositoryImpl implements CustomTokenRepository {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Boolean> isToken(String token) {
        Query query = new Query(Criteria.where("token").is(token));

        return mongoTemplate.findOne(query, Token.class)
                .map(s -> true)
                .defaultIfEmpty(false);
    }

    public Mono<Token> insertToken(Token token){
        mongoTemplate.indexOps(Token.class)
            .ensureIndex(new Index().on("createAt", Sort.Direction.ASC).expire(40));
        token.populateCreateAt();
        return mongoTemplate.save(token);
    }
}
