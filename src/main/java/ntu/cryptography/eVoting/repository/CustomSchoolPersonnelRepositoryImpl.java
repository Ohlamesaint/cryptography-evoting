package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.SchoolPersonnel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;

public class CustomSchoolPersonnelRepositoryImpl implements CustomSchoolPersonnelRepository{


    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public CustomSchoolPersonnelRepositoryImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<SchoolPersonnel> findSchoolPersonnelByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));

        return mongoTemplate.findOne(query, SchoolPersonnel.class);
    }
}
