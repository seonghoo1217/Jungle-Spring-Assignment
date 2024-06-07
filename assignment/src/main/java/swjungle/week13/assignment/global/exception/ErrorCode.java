package swjungle.week13.assignment.global.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import swjungle.week13.assignment.domain.exception.MemberNotFoundException;
import swjungle.week13.assignment.domain.exception.PasswordNotMatchException;
import swjungle.week13.assignment.domain.exception.UsernameExistException;

import java.util.Set;

@Getter
public enum ErrorCode {
    // Common
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 생겼습니다. 관리자에게 문의하세요.", Set.of()),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "입력 값이 올바르지 않습니다.",
            Set.of(MethodArgumentNotValidException.class, ConstraintViolationException.class)),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메서드입니다.", Set.of(HttpRequestMethodNotSupportedException.class)),

    USERNAME_EXIST(HttpStatus.BAD_REQUEST, "중복된 username 입니다.", Set.of(UsernameExistException.class)),
    NOTFOUND_MEMBER(HttpStatus.BAD_REQUEST, "회원을 찾지 못하였습니다.", Set.of(MemberNotFoundException.class)),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.", Set.of(PasswordNotMatchException.class));
    private final HttpStatusCode status;
    private final String code;
    private final String message;
    private final Set<Class<? extends Exception>> exceptions;

    ErrorCode(HttpStatusCode status, String code, String message, Set<Class<? extends Exception>> exceptions) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.exceptions = exceptions;
    }

    ErrorCode(HttpStatusCode status, String message, Set<Class<? extends Exception>> exceptions) {
        this.status = status;
        this.code = String.valueOf(status.value());
        this.message = message;
        this.exceptions = exceptions;
    }
}
