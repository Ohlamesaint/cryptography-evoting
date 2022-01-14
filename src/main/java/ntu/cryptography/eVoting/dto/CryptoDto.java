package ntu.cryptography.eVoting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CryptoDto {

    private String v;
    private String iv;
    private Object keys;
    private String cipher;
    private String signature;

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
