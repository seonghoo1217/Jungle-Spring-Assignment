package swjungle.week13.assignment.global.application;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swjungle.week13.assignment.domain.Member;
import swjungle.week13.assignment.domain.repo.MemberRepository;
import swjungle.week13.assignment.global.config.user.CustomUserDetails;
import swjungle.week13.assignment.global.config.user.dto.MemberDetails;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("400"));
        MemberDetails memberDetails = new MemberDetails(member.getId(), member.getUsername(), member.getPassword(), member.getMemberAuth());

        return new CustomUserDetails(memberDetails);
    }
}
