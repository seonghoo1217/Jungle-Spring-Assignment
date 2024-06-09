package swjungle.week13.assignment.domain.repo;

import org.springframework.data.repository.Repository;
import swjungle.week13.assignment.domain.Comment;

public interface CommentRepository extends Repository<Comment, Long> {
    Comment save(Comment comment);
}
