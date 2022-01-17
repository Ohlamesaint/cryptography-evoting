package ntu.cryptography.eVoting.repository;

import ntu.cryptography.eVoting.entity.Student;
import reactor.core.publisher.Mono;

public interface CustomStudentRepository {

    Mono<Student> isStudentExist(String studentId);

    Mono<Student> getByStudentId(String studentId);
}
