package swjungle.week13.assignment.domain.repo;

import org.springframework.data.repository.Repository;
import swjungle.week13.assignment.domain.Member;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {
    Optional<Member> findByUsername(String username);
}
