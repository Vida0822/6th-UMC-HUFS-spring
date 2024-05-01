package umc.umc_6th.apiPayload.code;

public interface BaseSuccessStatus {
    // 사용자 정의 응답 코드는 반드시 해당 두 메서드를 구현해야함
    public ReasonDTO getReasonDto() ;

    public ReasonDTO getReasonHttpStatus() ;

}
