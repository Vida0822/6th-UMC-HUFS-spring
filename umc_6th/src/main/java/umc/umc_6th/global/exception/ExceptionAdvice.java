package umc.umc_6th.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import umc.umc_6th.global.apiPayload.ApiResponse;
import umc.umc_6th.global.apiPayload.code.ErrorReasonDTO;
import umc.umc_6th.global.apiPayload.code.status.ErrorStatus;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    // 터진 에러를 수습해주는 클래스 --> RestController 안에서 터진 에러를 감지해서

    // ConstraintViolationException 처리
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
        // 예외 메시지를 추출합니다.
        String errorMessage = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생"));

        // 추출된 메시지를 기반으로 handleExceptionInternalConstraint를 호출합니다.
        return handleExceptionInternalConstraint(e, ErrorStatus.valueOf(errorMessage), HttpHeaders.EMPTY, request);
    }

    // MethodArgumentNotValidException 처리
    //@Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        // 에러를 저장할 맵을 생성합니다.
        Map<String, String> errors = new LinkedHashMap<>();

        // 에러를 추출하여 맵에 추가합니다.
        e.getBindingResult().getFieldErrors().stream()
                .forEach(fieldError -> {
                    String fieldName = fieldError.getField();
                    String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
                    errors.merge(fieldName, errorMessage, (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
                });

        // 추출된 에러들을 기반으로 handleExceptionInternalArgs를 호출합니다.
        return handleExceptionInternalArgs(e, HttpHeaders.EMPTY, ErrorStatus.valueOf("_BAD_REQUEST"), request, errors);
    }

    // 일반 예외 처리
    @ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        // 예외를 출력합니다.
        e.printStackTrace();

        // handleExceptionInternalFalse를 호출합니다.
        return handleExceptionInternalFalse(e, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, ErrorStatus._INTERNAL_SERVER_ERROR.getHttpstatus(), request, e.getMessage());
    }

    // GeneralException을 처리합니다.
    @ExceptionHandler(value = GeneralException.class)
    // 이 클래스에서 터진 GeneralException 에러 문제를 내개 해결해줄게!
    // @ExceptionHandler : 에러가 터졌을 때 생성자 매개변수에 들어갈 값들을 모아서 넣어줌
    public ResponseEntity onThrowException(GeneralException generalException, HttpServletRequest request) {
        // 에러 이유 및 HTTP 상태를 가져옵니다.
        ErrorReasonDTO errorReasonHttpStatus = generalException.getErrorReasonHttpStatus();
        return handleExceptionInternal(generalException, errorReasonHttpStatus, null, request);
    }

    // handleExceptionInternal의 구현.
    private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorReasonDTO reason,
                                                           HttpHeaders headers, HttpServletRequest request) {

        // 실패 응답 객체를 생성합니다.
        ApiResponse<Object> body = ApiResponse.onFailure(reason.getCode(), reason.getMessage(), null);

        // 예외를 처리하고 응답을 반환합니다.
        WebRequest webRequest = new ServletWebRequest(request);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                reason.getHttpstatus(),
                webRequest
        );
    }

    // handleExceptionInternalFalse의 구현.
    private ResponseEntity<Object> handleExceptionInternalFalse(Exception e, ErrorStatus errorCommonStatus,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request, String errorPoint) {
        // 실패 응답 객체를 생성합니다.
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), errorPoint);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                status,
                request
        );
    }

    // handleExceptionInternalArgs의 구현.
    private ResponseEntity<Object> handleExceptionInternalArgs(Exception e, HttpHeaders headers, ErrorStatus errorCommonStatus,
                                                               WebRequest request, Map<String, String> errorArgs) {
        // 실패 응답 객체를 생성합니다.
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), errorArgs);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCommonStatus.getHttpstatus(),
                request
        );
    }

    // handleExceptionInternalConstraint의 구현.
    private ResponseEntity<Object> handleExceptionInternalConstraint(Exception e, ErrorStatus errorCommonStatus,
                                                                     HttpHeaders headers, WebRequest request) {
        // 실패 응답 객체를 생성합니다.
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), null);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCommonStatus.getHttpstatus(),
                request
        );
    }
}
