package umc.umc_6th.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.umc_6th.apiPayload.code.status.ErrorStatus;
import umc.umc_6th.apiPayload.exception.handler.TempHandler;

@Service // 이 안의 ComponentScan이 있어야 Bean으로 등록 가능
@RequiredArgsConstructor 
public class TempQueryServiceImpl implements TempQueryService{
    @Override
    public void CheckFlag(Integer flag){
        if(flag==1)
            throw new TempHandler(ErrorStatus._TEMP_EXCEPTION) ;
    }
}
