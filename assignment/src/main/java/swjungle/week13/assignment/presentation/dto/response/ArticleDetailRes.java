package swjungle.week13.assignment.presentation.dto.response;

import swjungle.week13.assignment.application.dto.CommentDTO;
import swjungle.week13.assignment.domain.Article;
import swjungle.week13.assignment.domain.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ArticleDetailRes(UUID uuid, String title, String contents, String username, LocalDateTime postTime,
                               List<CommentDTO> comments) {
    public ArticleDetailRes(Article article) {
        this(
                article.getUuid(),
                article.getArticleEssential().getTitle(),
                article.getArticleEssential().getContents(),
                article.getArticleEssential().getAuthor(),
                article.getArticleEssential().getPostDateTime(),
                convertCommentEntityToDTO(article.getComments())
        );
    }

    private static List<CommentDTO> convertCommentEntityToDTO(List<Comment> comments) {
        return comments.stream()
                .map(c -> new CommentDTO(c.getUuid(), c.getContents(), c.getPostDateTime(), c.getMember().getUsername(), c.getArticle().getUuid()))
                .toList();
    }
}
