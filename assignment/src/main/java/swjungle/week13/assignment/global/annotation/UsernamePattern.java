package swjungle.week13.assignment.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import swjungle.week13.assignment.global.annotation.validator.UsernameValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface UsernamePattern {
    String message() default "아이디는 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)를 포함하여야합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
