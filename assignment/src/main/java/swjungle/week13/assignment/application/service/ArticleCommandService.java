package swjungle.week13.assignment.application.service;

import swjungle.week13.assignment.domain.Article;

public interface ArticleCommandService {
    Article createArticle(String title, String contents, String username);
}
