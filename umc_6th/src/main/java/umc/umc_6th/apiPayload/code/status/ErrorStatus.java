package umc.umc_6th.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.umc_6th.apiPayload.code.BaseErrorStatus;
import umc.umc_6th.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorStatus {
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다") ,
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400" , "잘못된 요청입니다") ,
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다"),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403" , "금지된 요청입니다."),

    // 멤버 관련 에러응답
    _MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다.") , // JWT 토큰 안줌
    _NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수입니다."), // 회원 정보 누락

    // 미션 관련 에러
    _MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION4001", "해당 미션이 없습니다"),
    
    // test
    _TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트")

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
