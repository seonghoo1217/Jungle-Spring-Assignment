package swjungle.week13.assignment.application.service;

import org.springframework.data.domain.Page;
import swjungle.week13.assignment.application.dto.ArticleDTO;
import swjungle.week13.assignment.presentation.dto.response.ArticleDetailRes;

import java.util.UUID;

public interface ArticleQueryService {
    Page<ArticleDTO> findAllArticles(int page, int size);

    ArticleDetailRes findByUuid(UUID uuid);
}
