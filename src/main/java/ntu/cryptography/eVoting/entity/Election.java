package ntu.cryptography.eVoting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Election {

    @Id
    private String _id;
    private String name;

    @CreatedDate
    private String createDate;
    @LastModifiedDate
    private String lastModifiedDay;

    private String publicKey;

    @JsonIgnore
    private String privateKey;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
