package umc.umc_6th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@Getter
public class ErrorReasonDTO {
    private final String code ;
    private final String message ;
    private final Boolean isSuccess ;
    private final HttpStatus httpstatus ;
}
