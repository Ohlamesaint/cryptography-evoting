package ntu.cryptography.eVoting.dto;

import lombok.*;
import ntu.cryptography.eVoting.entity.Election;
import ntu.cryptography.eVoting.entity.Participate;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VoteDto {

    private String _id;
    private int candidateNumber;
    private String electionId;

    public VoteDto(int candidateNumber, String electionId) {
        this.candidateNumber = candidateNumber;
        this.electionId = electionId;
    }

    public int getCandidateNumber() {
        return candidateNumber;
    }

    public void setCandidateNumber(int candidateNumber) {
        this.candidateNumber = candidateNumber;
    }

    public String getElectionId() {
        return electionId;
    }

    public void setElectionId(String electionId) {
        this.electionId = electionId;
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
