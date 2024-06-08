package swjungle.week13.assignment.application.service;

import org.springframework.data.domain.Page;
import swjungle.week13.assignment.application.dto.ArticleDTO;

public interface ArticleQueryService {
    Page<ArticleDTO> findAllArticles(int page, int size);
}
