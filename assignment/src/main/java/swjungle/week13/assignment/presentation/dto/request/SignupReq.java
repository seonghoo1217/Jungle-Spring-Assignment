package swjungle.week13.assignment.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import swjungle.week13.assignment.global.annotation.PasswordPattern;
import swjungle.week13.assignment.global.annotation.UsernamePattern;

public record SignupReq(@UsernamePattern @NotBlank String username, @PasswordPattern @NotBlank String password) {
}
