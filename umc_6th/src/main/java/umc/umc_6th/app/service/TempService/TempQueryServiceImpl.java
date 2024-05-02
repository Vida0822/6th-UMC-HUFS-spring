package umc.umc_6th.app.service.TempService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.umc_6th.global.apiPayload.code.status.ErrorStatus;
import umc.umc_6th.global.exception.handler.TempHandler;

@Service // 이 안의 ComponentScan이 있어야 Bean으로 등록 가능
@RequiredArgsConstructor 
public class TempQueryServiceImpl implements TempQueryService{
    @Override
    public void CheckFlag(Integer flag){
        if(flag==1)
            throw new TempHandler(ErrorStatus._TEMP_EXCEPTION) ;
            // TEMP_EXCEPTION 객체를 가져와서 TempHandler 에서 발생시킴
    }
}
