package swjungle.week13.assignment.application.dto;

import swjungle.week13.assignment.domain.Comment;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentDTO(UUID uuid, String contents, LocalDateTime postTime, String username, UUID articleUuid) {

    public CommentDTO(Comment comment) {
        this(
                comment.getUuid(),
                comment.getContents(),
                comment.getPostDateTime(),
                comment.getMember().getUsername(),
                comment.getArticle().getUuid());
    }
}
