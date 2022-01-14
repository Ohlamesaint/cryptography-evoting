package ntu.cryptography.eVoting.util;

import ntu.cryptography.eVoting.dto.*;
import ntu.cryptography.eVoting.entity.*;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    // election
    public static ElectionDto toDto(Election election){
        ElectionDto dto = new ElectionDto();

        BeanUtils.copyProperties(election, dto);
        return dto;
    }

    public static Election toEntity(ElectionDto dto){
        Election election = new Election();

        BeanUtils.copyProperties(dto, election);
        return election;
    }

    // school personnel
    public static SchoolPersonnelDto toDto(SchoolPersonnel schoolPersonnel){
        SchoolPersonnelDto dto = new SchoolPersonnelDto();

        BeanUtils.copyProperties(schoolPersonnel, dto);
        System.out.println(dto);
        return dto;
    }

    public static SchoolPersonnel toEntity(SchoolPersonnelDto dto){
        SchoolPersonnel schoolPersonnel = new SchoolPersonnel();

        BeanUtils.copyProperties(dto, schoolPersonnel);
        System.out.println(schoolPersonnel);
        return schoolPersonnel;
    }

    // student
    public static StudentDto toDto(Student student){
        StudentDto dto = new StudentDto();

        BeanUtils.copyProperties(student, dto);
        System.out.println(dto);
        return dto;
    }

    public static Student toEntity(StudentDto dto){
        Student student = new Student();

        BeanUtils.copyProperties(dto, student);
        System.out.println(student);
        return student;
    }

    // token
    public static TokenDto toDto(Token token){
        TokenDto dto = new TokenDto();

        BeanUtils.copyProperties(token, dto);
        System.out.println(dto);
        return dto;
    }

    public static Token toEntity(TokenDto dto){
        Token token = new Token();

        BeanUtils.copyProperties(dto, token);
        System.out.println(token);
        return token;
    }

    // Participate
    public static ParticipateDto toDto(Participate Participate){
        ParticipateDto dto = new ParticipateDto();

        BeanUtils.copyProperties(Participate, dto);
        System.out.println(dto);
        return dto;
    }

    public static Participate toEntity(ParticipateDto dto){
        Participate participate = new Participate();

        BeanUtils.copyProperties(dto, participate);
        System.out.println(participate);
        return participate;
    }

    // vote
    public static VoteDto toDto(Vote vote){
        VoteDto dto = new VoteDto();

        BeanUtils.copyProperties(vote, dto);
        System.out.println(dto);
        return dto;
    }

    public static Vote toEntity(VoteDto dto){
        Vote vote = new Vote();

        BeanUtils.copyProperties(dto, vote);
        System.out.println(vote);
        return vote;
    }
}
