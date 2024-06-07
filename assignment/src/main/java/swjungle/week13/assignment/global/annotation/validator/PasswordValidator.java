package swjungle.week13.assignment.global.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import swjungle.week13.assignment.global.annotation.PasswordPattern;
import swjungle.week13.assignment.global.dto.ResponseEnvelope;

public class PasswordValidator implements ConstraintValidator<PasswordPattern, String> {
    private static final String UUID_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,15}$";


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || !value.matches(UUID_REGEX)) {
            String errorMessage = context.getDefaultConstraintMessageTemplate();
            ResponseEnvelope<Void> errorResponse = ResponseEnvelope.of("INVALID_PASSWORD", null, errorMessage);
            /*context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorResponse.toString())
                    .addConstraintViolation();*/
            return false;
        }
        return true;
    }
}
