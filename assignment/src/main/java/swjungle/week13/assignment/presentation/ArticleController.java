package swjungle.week13.assignment.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import swjungle.week13.assignment.application.dto.ArticleDTO;
import swjungle.week13.assignment.application.service.ArticleCommandService;
import swjungle.week13.assignment.application.service.ArticleQueryService;
import swjungle.week13.assignment.domain.Article;
import swjungle.week13.assignment.global.dto.ResponseEnvelope;
import swjungle.week13.assignment.presentation.dto.request.CreateArticleReq;
import swjungle.week13.assignment.presentation.dto.request.ModifyArticleReq;
import swjungle.week13.assignment.presentation.dto.request.PageReq;
import swjungle.week13.assignment.presentation.dto.response.ArticleCreateRes;
import swjungle.week13.assignment.presentation.dto.response.ArticleDetailRes;
import swjungle.week13.assignment.presentation.dto.response.PageRes;

import java.util.UUID;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleCommandService articleCommandService;
    private final ArticleQueryService articleQueryService;

    @PostMapping
    public ResponseEnvelope<?> createArticle(@Valid @RequestBody CreateArticleReq articleReq, HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        Article article = articleCommandService.createArticle(articleReq.title(), articleReq.contents(), authorization);
        return ResponseEnvelope.of(new ArticleCreateRes(article));
    }

    @GetMapping
    public ResponseEnvelope<?> findAllArticles(@Valid PageReq pageReq) {
        Page<ArticleDTO> articles = articleQueryService.findAllArticles(pageReq.page(), pageReq.size());
        return ResponseEnvelope.of(new PageRes<>(articles));
    }

    @GetMapping("{uuid}")
    public ResponseEnvelope<?> findArticleByUuid(@PathVariable("uuid") UUID uuid) {
        ArticleDetailRes articleDetailRes = articleQueryService.findByUuid(uuid);
        return ResponseEnvelope.of(articleDetailRes);
    }

    @PatchMapping
    public ResponseEnvelope<?> modifyArticleEssential(@RequestBody ModifyArticleReq modifyArticleReq, HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        ArticleDetailRes article = articleCommandService.modifyArticleEssential(authorization, modifyArticleReq.uuid(),
                modifyArticleReq.title(), modifyArticleReq.contents());

        return ResponseEnvelope.of(article);
    }

    @DeleteMapping("{uuid}")
    public ResponseEnvelope<?> deleteArticle(@PathVariable("uuid") UUID uuid, HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");

        articleCommandService.deleteArticle(authorization, uuid);

        return ResponseEnvelope.of("");
    }
}
