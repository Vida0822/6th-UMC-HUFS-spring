package umc.umc_6th.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
public class ErrorReasonDTO {
    private final String code ;
    private final String message ;
    private final Boolean isSuccess ;
    private final HttpStatus httpstatus ;
}
