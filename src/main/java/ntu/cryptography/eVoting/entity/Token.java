package ntu.cryptography.eVoting.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.Date;

@Data
@Document
public class Token {

    @Id
    private String _id;

    private String token;

    @Field
    @Indexed(name="createAtIndex", expireAfterSeconds = 1800)
    private Date createAt;

    public void populateCreateAt(){
        this.createAt = Date.from(Instant.now());
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
