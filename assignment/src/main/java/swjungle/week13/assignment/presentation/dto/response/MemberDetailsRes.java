package swjungle.week13.assignment.presentation.dto.response;

public record MemberDetailsRes(String username) {
    public MemberDetailsRes(SignupRes signupRes) {
        this(signupRes.username());
    }
}
