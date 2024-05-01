package umc.umc_6th.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.umc_6th.apiPayload.code.BaseErrorStatus;
import umc.umc_6th.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{

    private BaseErrorStatus code ;

    public ErrorReasonDTO getErrorReason(){
        return this.code.getReasonDto() ;
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus() ;
    }
}
