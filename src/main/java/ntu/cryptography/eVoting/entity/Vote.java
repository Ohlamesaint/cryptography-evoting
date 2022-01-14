package ntu.cryptography.eVoting.entity;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
public class Vote {

    @Id
    private String _id;

    private int candidateNumber;

    private String electionId;

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
