package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import reactor.core.publisher.Mono;


public class CustomStudentRepositoryImpl implements CustomStudentRepository{

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Student> isStudentExist(String studentId) {
        Query query = new Query(Criteria.where("studentId").is(studentId));

        return reactiveMongoTemplate
                .findOne(query, Student.class);
    }
}
