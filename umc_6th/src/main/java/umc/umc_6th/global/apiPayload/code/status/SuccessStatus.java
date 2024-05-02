package umc.umc_6th.global.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.umc_6th.global.apiPayload.code.BaseSuccessStatus;
import umc.umc_6th.global.apiPayload.code.ReasonDTO;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseSuccessStatus {
    _OK(HttpStatus.OK, "COMMON200", "정상적으로 처리되었습니다")
    ;
    private final HttpStatus httpstatus ;
    private final String code ;
    private final String message ;

    @Override
    public ReasonDTO getReasonDto() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpstatus(httpstatus)
                .build();
    }
}
