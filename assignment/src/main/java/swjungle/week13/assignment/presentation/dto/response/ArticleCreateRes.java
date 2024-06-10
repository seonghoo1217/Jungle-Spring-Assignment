package swjungle.week13.assignment.presentation.dto.response;

import swjungle.week13.assignment.domain.Article;

import java.time.LocalDateTime;
import java.util.UUID;

public record ArticleCreateRes(UUID uuid, String title, String contents, String username, LocalDateTime postTime) {
    public ArticleCreateRes(Article article) {
        this(article.getUuid(),
                article.getArticleEssential().getTitle(),
                article.getArticleEssential().getContents(),
                article.getArticleEssential().getAuthor(),
                article.getArticleEssential().getPostDateTime());
    }
}

