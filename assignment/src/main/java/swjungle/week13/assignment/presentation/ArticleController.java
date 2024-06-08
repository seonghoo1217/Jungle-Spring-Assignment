package swjungle.week13.assignment.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import swjungle.week13.assignment.application.service.ArticleCommandService;
import swjungle.week13.assignment.domain.Article;
import swjungle.week13.assignment.global.dto.ResponseEnvelope;
import swjungle.week13.assignment.presentation.dto.request.CreateArticleReq;
import swjungle.week13.assignment.presentation.dto.response.ArticleDetailRes;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleCommandService articleCommandService;

    @PostMapping
    public ResponseEnvelope<?> createArticle(@Valid @RequestBody CreateArticleReq articleReq) {
        Article article = articleCommandService.createArticle(articleReq.title(), articleReq.contents(), articleReq.username());
        return ResponseEnvelope.of(new ArticleDetailRes(article));
    }
}
