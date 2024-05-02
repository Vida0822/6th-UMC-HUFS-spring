package umc.umc_6th.global.exception.handler;

import umc.umc_6th.global.apiPayload.code.BaseErrorStatus;
import umc.umc_6th.global.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorStatus errorStatus){
        super(errorStatus) ; // GeneralException 생성
    }

}
