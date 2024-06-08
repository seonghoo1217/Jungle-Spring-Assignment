package swjungle.week13.assignment.domain.repo;

import org.springframework.data.repository.Repository;
import swjungle.week13.assignment.domain.Article;

public interface ArticleRepository extends Repository<Article, Long> {
    Article save(Article article);
}
