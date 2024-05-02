package umc.umc_6th.app.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.umc_6th.app.web.dto.TempResponse;
import umc.umc_6th.global.apiPayload.ApiResponse;
import umc.umc_6th.app.TempConverter;
import umc.umc_6th.app.service.TempService.TempQueryService;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {

    private final TempQueryService tempQueryService  ;

    @GetMapping("/test")
    public ApiResponse<TempResponse.TempTestDTO> testApi(){
        return ApiResponse.onSuccess((TempConverter.toTempTestDTO())) ;
    }

    @GetMapping("/exception")
    public ApiResponse<TempResponse.TempExceptionDTO> exceptionTestApi(@RequestParam Integer flag){
        tempQueryService.CheckFlag(flag) ; // flag가 1이면 예외(TempHanler-TempException) 발생
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag)) ;
    }
}
