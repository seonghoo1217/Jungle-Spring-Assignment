package swjungle.week13.assignment.global.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import swjungle.week13.assignment.global.annotation.UsernamePattern;

public class UsernameValidator implements ConstraintValidator<UsernamePattern, String> {
    private static final String UUID_REGEX = "^(?=.*[a-zA-Z])(?=.*[0-9]).{4,10}$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || !value.matches(UUID_REGEX)) {
//            String errorMessage = context.getDefaultConstraintMessageTemplate();
//            ResponseEnvelope<Void> errorResponse = ResponseEnvelope.of("INVALID_USERNAME", null, errorMessage);
            /*context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorResponse.toString())
                    .addConstraintViolation();*/
            return false;
        }
        return true;
    }
}
