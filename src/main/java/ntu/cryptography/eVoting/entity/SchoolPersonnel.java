package ntu.cryptography.eVoting.entity;

import lombok.Data;
import lombok.Generated;
import ntu.cryptography.eVoting.Authentication.Role;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
public class SchoolPersonnel {

    @Id
    private String id;
    private String username;
    private String password;
    private boolean enabled;
    private List<Role> roles;

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
