package swjungle.week13.assignment.application.service;

import swjungle.week13.assignment.domain.Article;
import swjungle.week13.assignment.presentation.dto.response.ArticleDetailRes;

import java.util.UUID;

public interface ArticleCommandService {
    Article createArticle(String title, String contents, String username);

    ArticleDetailRes modifyArticleEssential(UUID uuid, String title, String contents);

    void deleteArticle(UUID uuid);
}
