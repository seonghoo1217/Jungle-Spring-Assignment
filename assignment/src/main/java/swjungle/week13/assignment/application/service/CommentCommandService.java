package swjungle.week13.assignment.application.service;

import swjungle.week13.assignment.domain.Comment;

import java.util.UUID;

public interface CommentCommandService {
    Comment applyCommentToArticle(UUID uuid, String contents, String authorization);

    Comment modifyComment(UUID commentUuid, String contents, String authorization);

    void deleteComment(UUID commentUuid, String authorization);
}
