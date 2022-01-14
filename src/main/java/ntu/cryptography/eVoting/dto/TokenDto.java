package ntu.cryptography.eVoting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TokenDto {

    private String token;
    private String id;
    private Date createAt;

    public TokenDto(String token) {
        this.token = token;
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
