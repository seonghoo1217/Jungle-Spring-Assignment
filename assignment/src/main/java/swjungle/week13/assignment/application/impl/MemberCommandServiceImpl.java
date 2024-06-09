package swjungle.week13.assignment.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swjungle.week13.assignment.application.service.MemberCommandService;
import swjungle.week13.assignment.domain.Member;
import swjungle.week13.assignment.domain.MemberAuth;
import swjungle.week13.assignment.domain.exception.UsernameExistException;
import swjungle.week13.assignment.domain.repo.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Long signUp(String username, String password) {
        if (memberRepository.existUsername(username)) {
            throw new UsernameExistException();
        }

        MemberAuth auth = "ADMIN1234".equals(username) ? MemberAuth.ADMIN : MemberAuth.USER;
        Member member = new Member(username, passwordEncoder.encode(password), auth);
        return memberRepository.save(member).getId();
    }


}
