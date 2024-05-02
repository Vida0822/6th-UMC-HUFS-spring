package umc.umc_6th.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.umc_6th.global.apiPayload.code.BaseErrorStatus;
import umc.umc_6th.global.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{

    private BaseErrorStatus code ; // 생성자로 전달받는 error_Status 전달받음

    public ErrorReasonDTO getErrorReason(){
        return this.code.getReasonDto() ;
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus() ;
    }
}
