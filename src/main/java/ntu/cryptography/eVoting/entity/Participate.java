package ntu.cryptography.eVoting.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Participate {

    @Id
    private String _id;
    private String politics;
    private int candidateNumber;
    private long result;

    // foreign key
    private String studentId;
    private String electionId;
}
