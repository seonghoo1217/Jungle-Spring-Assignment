package swjungle.week13.assignment.global.config.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import swjungle.week13.assignment.global.config.user.dto.MemberDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public record CustomUserDetails(MemberDetails memberDetails) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_" + memberDetails.memberAuth().toString());


        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return memberDetails.password();
    }

    @Override
    public String getUsername() {
        return memberDetails.username();
    }
}
