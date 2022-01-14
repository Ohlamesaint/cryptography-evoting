package ntu.cryptography.eVoting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
@NoArgsConstructor
public class ParticipateDto {

    private String _id;
    private String politics;
    private int candidateNumber;
    private long result = 0;

    // foreign key
    private String studentId;
    private String electionId;

    public ParticipateDto(String politics, int candidateNumber, String studentId, String electionId) {
        this.politics = politics;
        this.candidateNumber = candidateNumber;
        this.studentId = studentId;
        this.electionId = electionId;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public int getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(int candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getElectionId() {
        return electionId;
    }

    public void setResult(long result){
        this.result = result;
    }

    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
