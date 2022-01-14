package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String>, CustomStudentRepository {
}
