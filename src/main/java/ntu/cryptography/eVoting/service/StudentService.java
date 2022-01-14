package ntu.cryptography.eVoting.service;

import ntu.cryptography.eVoting.dto.StudentDto;
import ntu.cryptography.eVoting.dto.TokenDto;
import ntu.cryptography.eVoting.entity.Student;
import ntu.cryptography.eVoting.repository.StudentRepository;
import ntu.cryptography.eVoting.util.AnsiColor;
import ntu.cryptography.eVoting.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Flux<StudentDto> getAllStudent(){
        System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] in get all student"+AnsiColor.ANSI_RESET);
        return this.studentRepository.findAll().map(EntityDtoUtil::toDto)
                .doOnError(e -> System.out.println("something wrong"))
                .doAfterTerminate(() -> System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] out of get all student"+AnsiColor.ANSI_RESET));
    }

    public Mono<StudentDto> insertStudent(Mono<StudentDto> dto){
        System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] in insert student"+AnsiColor.ANSI_RESET);
        return dto
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.studentRepository::insert)
                .map(EntityDtoUtil::toDto)
                .doOnError(e -> System.out.println(e.getMessage()))
                .doAfterTerminate(() -> System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] out of insert student"+AnsiColor.ANSI_RESET));
    }

    public Mono<StudentDto> isStudentExist(String studentId){
        System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] in isStudentExist"+AnsiColor.ANSI_RESET);
        return this.studentRepository.isStudentExist(studentId)
                .map(s -> (Student) s)
                .map(EntityDtoUtil::toDto)
                .switchIfEmpty(Mono.empty());
    }

}
