package umc.umc_6th.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.umc_6th.apiPayload.code.BaseErrorStatus;
import umc.umc_6th.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum CommonErrorStatus implements BaseErrorStatus {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다") ,
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400" , "잘못된 요청입니다") ,
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다"),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403" , "금지된 요청입니다."),

    // 멤버 관련 에러응답
    _MEMBER_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON501", "서버 에러, 관리자에게 문의 바랍니다") ,

    ;

    private final HttpStatus httpstatus ;
    private final String code ;
    private final String message ;

    @Override
    public ErrorReasonDTO getReasonDto() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build() ;
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpstatus(httpstatus)
                .build() ;
    }
}
