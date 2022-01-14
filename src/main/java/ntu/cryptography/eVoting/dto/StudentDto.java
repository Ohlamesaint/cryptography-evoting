package ntu.cryptography.eVoting.dto;

import lombok.*;
import ntu.cryptography.eVoting.enums.Gender;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {

    private String _id;
    private Gender gender;
    private int grade;
    private String department;
    private String name;
    private String studentId;

    public StudentDto(String id, String gender, int grade, String department, String name, String studentId) {

        if(gender.equalsIgnoreCase("FEMALE")) this.gender = Gender.FEMALE;
        else this.gender = Gender.MALE;

        this.grade = grade;
        this.department = department;
        this.name = name;
        this.studentId = studentId;
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
