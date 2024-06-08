package swjungle.week13.assignment.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swjungle.week13.assignment.application.service.MemberQueryService;
import swjungle.week13.assignment.domain.Member;
import swjungle.week13.assignment.domain.exception.MemberNotFoundException;
import swjungle.week13.assignment.domain.exception.PasswordNotMatchException;
import swjungle.week13.assignment.domain.repo.MemberRepository;
import swjungle.week13.assignment.global.application.JwtUtil;
import swjungle.week13.assignment.presentation.dto.response.SignupRes;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public SignupRes signin(String username, String password) {
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);

        if (!isMatchPassword(member.getPassword(), password)) {
            throw new PasswordNotMatchException();
        }
        String accessToken = jwtUtil.createAccessToken(member);
        String refreshToken = jwtUtil.createRefreshToken(member);
        member.updateRefreshToken(refreshToken);

        return new SignupRes(member.getUsername(), accessToken, refreshToken);
    }

    private boolean isMatchPassword(String dbPassword, String reqPassword) {
        return passwordEncoder.matches(reqPassword, dbPassword);
    }
}
