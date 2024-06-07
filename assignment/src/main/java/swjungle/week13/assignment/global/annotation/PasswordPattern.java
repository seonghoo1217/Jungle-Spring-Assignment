package swjungle.week13.assignment.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import swjungle.week13.assignment.global.annotation.validator.PasswordValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface PasswordPattern {
    String message() default "비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자를 포함하여야합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
