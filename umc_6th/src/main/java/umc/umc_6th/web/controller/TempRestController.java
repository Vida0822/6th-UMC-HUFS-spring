package umc.umc_6th.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.umc_6th.apiPayload.ApiResponse;
import umc.umc_6th.converter.TempConverter;
import umc.umc_6th.service.TempService.TempQueryService;
import umc.umc_6th.web.dto.TempResponse;

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
        tempQueryService.CheckFlag(flag) ;
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag)) ;
    }
}
