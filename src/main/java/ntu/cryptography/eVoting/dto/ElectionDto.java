package ntu.cryptography.eVoting.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ntu.cryptography.eVoting.util.DateTimeOperator;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ElectionDto {

    private String _id;
    private String name;
    private String createDate;
    private String lastModifiedDay;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String privateKey;
    private String publicKey;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private List<String> electionItem;  // 選舉項目

    public ElectionDto(String id, String name, List<String> electionItem, String startTime, String endTime) {
        this._id = id;
        this.name = name;
        this.electionItem.addAll(electionItem);
        this.startTime = DateTimeOperator.StringToIsoDate(startTime);
        this.endTime = DateTimeOperator.StringToIsoDate(endTime);
    }

    public String toString(){
        return ReflectionToStringBuilder.toString(this);
    }
}
