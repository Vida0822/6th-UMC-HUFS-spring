package umc.umc_6th.apiPayload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.umc_6th.apiPayload.code.BaseSuccessStatus;
import umc.umc_6th.apiPayload.code.status.CommonSuccessStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
// JSON 직렬화 시 속성들의 순서를 지정하는 어노테이션 : "isSuccess", "code", "message", "result" 순서로 직렬화된다.
public class ApiResponse<T> {
    // API 응답 통일을 위한 클래스 => JSON 출력 형식 및 데이터를 구체적으로 조정해준다

    @JsonProperty("isSuccess")
    // 이 필드는 JSON의 "isSuccess"라는 이름으로 매핑된다.
    private final Boolean isSuccess ; // 성공인지 아닌지 알려주는 필드

    @JsonProperty("code")
    // 이 필드는 JSON의 "code"라는 이름으로 매핑된다.
    private final String code ; // Http 상태코드보다 세부적인 응답 상황을 알려주기 위한 사용자 정의 응답코드

    @JsonProperty("message")
    // 이 필드는 JSON의 "message"라는 이름으로 매핑된다.
    private final String message ; // code에 추가적으로 설명을 달아주는 필드 (어떤 결과인지 설명)

    @JsonProperty("result")
    // 이 필드는 JSON의 "result"라는 이름으로 매핑된다.
    @JsonInclude(JsonInclude.Include.NON_NULL)
    // result 필드가 null일 경우 JSON에 직렬화하지 않도록 지정
    private T result ;
    // 실제로 클라이언트에게 필요한 데이터
    // --> 어떤 형태의 값이 올지 모르므로, 어떤 클래스 종류이던 담을 수 있는 제네릭 클래스(T) 사용

    // 성공한 경우 응답 생성
    public static <T> ApiResponse<T> onSuccess(T result){
        return new ApiResponse<>(true, CommonSuccessStatus._OK.getCode(), CommonSuccessStatus._OK.getMessage(), result) ;
    }

    public static <T> ApiResponse<T> of(BaseSuccessStatus code , T result){
        return new ApiResponse<>(true, code.getReasonHttpStatus().getCode(), code.getReasonHttpStatus().getMessage() ,result) ;
    }


    // 실패한 경우 응답 생성
    public static <T> ApiResponse<T> onFailure(String code, String message, T data){
        return new ApiResponse<>(true, code, message, data) ; 
    }
}
