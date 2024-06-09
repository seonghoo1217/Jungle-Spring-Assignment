package swjungle.week13.assignment.presentation.dto.response;

import swjungle.week13.assignment.domain.Comment;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentDetailRes(UUID commentUuid, String contents, LocalDateTime postTime, String authorName) {

    public CommentDetailRes(Comment comment) {
        this(comment.getUuid(), comment.getContents(), comment.getPostDateTime(), comment.getMember().getUsername());
    }
}
