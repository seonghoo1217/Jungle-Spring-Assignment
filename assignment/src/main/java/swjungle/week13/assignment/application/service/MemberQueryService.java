package swjungle.week13.assignment.application.service;

import swjungle.week13.assignment.presentation.dto.response.SignupRes;

public interface MemberQueryService {
    SignupRes signin(String username, String password);
}
