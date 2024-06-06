package swjungle.week13.assignment.domain.repo;

import org.springframework.data.repository.Repository;
import swjungle.week13.assignment.domain.Member;
import swjungle.week13.assignment.domain.repo.querydsl.MemberDslRepository;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long>, MemberDslRepository {
    Optional<Member> findByUsername(String username);

    Member save(Member member);
}
