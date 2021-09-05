package id.go.dephub.hubla.sehati.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected Long total;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected T data;

    public GeneralResponse(int status,String message){
        super();
        this.status=status;
        this.message=message;
        this.total=null;
    }

}
