package umc.umc_6th.apiPayload.exception.handler;

import umc.umc_6th.apiPayload.code.BaseErrorStatus;
import umc.umc_6th.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorStatus errorStatus){
        super(errorStatus) ;
    }

}
