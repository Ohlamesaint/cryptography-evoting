package ntu.cryptography.eVoting.service;

import ntu.cryptography.eVoting.Authentication.Role;
import ntu.cryptography.eVoting.dto.SchoolPersonnelDto;
import ntu.cryptography.eVoting.repository.SchoolPersonnelRepository;
import ntu.cryptography.eVoting.util.AnsiColor;
import ntu.cryptography.eVoting.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SchoolPersonnelService {

    @Autowired
    private SchoolPersonnelRepository schoolPersonnelRepository;


    public Flux<SchoolPersonnelDto> getAll(){
        System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] in get all school personnel"+AnsiColor.ANSI_RESET);
        return this.schoolPersonnelRepository
                .findAll()
                .map(EntityDtoUtil::toDto)
                .doOnError(e -> System.out.println(e.getMessage()))
                .doAfterTerminate(() -> System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] out of get all school personnel"+AnsiColor.ANSI_RESET));
    }

    public Mono<SchoolPersonnelDto> getSchoolPersonnelByUsername(String username){
        System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] in get school personnel by username"+AnsiColor.ANSI_RESET);
        return this.schoolPersonnelRepository.findSchoolPersonnelByUsername(username)
                        .map(EntityDtoUtil::toDto)
                        .doOnError(e -> System.out.println(e.getMessage()))
                        .doAfterTerminate(() -> System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] out of get school personnel by username"+AnsiColor.ANSI_RESET));
    }

    public Mono<SchoolPersonnelDto> insert(Mono<SchoolPersonnelDto> dto){
        System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] in insert school personnel"+AnsiColor.ANSI_RESET);
        return dto
            .map(EntityDtoUtil::toEntity)
            .flatMap(this.schoolPersonnelRepository::insert)
            .map(EntityDtoUtil::toDto)
            .doOnError(e -> System.out.println(e.getMessage()))
            .doAfterTerminate(() -> System.out.println(AnsiColor.ANSI_GREEN+"[Service Layer] out of insert school personnel"+AnsiColor.ANSI_RESET));
    }
}
