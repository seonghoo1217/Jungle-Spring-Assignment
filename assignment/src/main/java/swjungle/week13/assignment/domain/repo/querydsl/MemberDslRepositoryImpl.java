package swjungle.week13.assignment.domain.repo.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import swjungle.week13.assignment.domain.QMember;

@Repository
@RequiredArgsConstructor
public class MemberDslRepositoryImpl implements MemberDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public boolean existUsername(String username) {
        QMember member = QMember.member;
        Integer fetchResult = jpaQueryFactory
                .selectOne()
                .from(member)
                .where(member.username.eq(username))
                .fetchFirst();

        return fetchResult != null;
    }
}
