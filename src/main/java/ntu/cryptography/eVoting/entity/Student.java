package ntu.cryptography.eVoting.entity;

import lombok.Data;
import ntu.cryptography.eVoting.enums.Gender;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;

@Data
public class Student {

    @Id
    private String _id;
    private Gender gender;
    private int grade;
    private String department;
    private String name;
    private String studentId;

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
