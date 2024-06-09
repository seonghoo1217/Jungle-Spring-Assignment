package swjungle.week13.assignment.application.dto;

import swjungle.week13.assignment.domain.Article;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ArticleDTO(UUID uuid,
                         String title,
                         String contents,
                         String username,
                         LocalDateTime postDateTime,
                         List<CommentDTO> comments) {


    public ArticleDTO(Article article, List<CommentDTO> comments) {
        this(article.getUuid(),
                article.getArticleEssential().getTitle(),
                article.getArticleEssential().getContents(),
                article.getMember().getUsername(),
                article.getArticleEssential().getPostDateTime(),
                comments);
    }
}
