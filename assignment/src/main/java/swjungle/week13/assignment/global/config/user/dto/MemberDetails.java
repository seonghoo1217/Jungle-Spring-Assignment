package swjungle.week13.assignment.global.config.user.dto;

import swjungle.week13.assignment.domain.MemberAuth;

public record MemberDetails(Long id, String username, String password, MemberAuth memberAuth) {
}
