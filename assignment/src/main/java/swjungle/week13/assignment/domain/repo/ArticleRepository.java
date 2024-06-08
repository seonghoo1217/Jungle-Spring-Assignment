package swjungle.week13.assignment.domain.repo;

import org.springframework.data.repository.Repository;
import swjungle.week13.assignment.domain.Article;

import java.util.Optional;
import java.util.UUID;

public interface ArticleRepository extends Repository<Article, Long> {
    Article save(Article article);

    Optional<Article> findByUuid(UUID uuid);

    void delete(Article article);
}
