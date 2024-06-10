package swjungle.week13.assignment.application.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swjungle.week13.assignment.application.dto.ArticleDTO;
import swjungle.week13.assignment.application.dto.CommentDTO;
import swjungle.week13.assignment.application.service.ArticleQueryService;
import swjungle.week13.assignment.domain.Article;
import swjungle.week13.assignment.domain.exception.ArticleNotFoundException;
import swjungle.week13.assignment.domain.repo.ArticleRepository;
import swjungle.week13.assignment.global.dto.CustomPageable;
import swjungle.week13.assignment.presentation.dto.response.ArticleDetailRes;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final JPAQueryFactory jpaQueryFactory;
    private final ArticleRepository articleRepository;

    @Override
    public Page<ArticleDTO> findAllArticles(int page, int size) {
//        QArticle article = QArticle.article;
//        QComment comment = QComment.comment;
        Pageable pageable = CustomPageable.of(page, size, Sort.by("articleEssential.postDateTime").descending());
        Page<Article> articles = articleRepository.findAll(pageable);

        return articles.map(article -> {
            List<CommentDTO> comments = article.getComments().stream()
                    .map(CommentDTO::new)
                    .collect(Collectors.toList());
            return new ArticleDTO(article, comments);
        });
    }


    @Override
    public ArticleDetailRes findByUuid(UUID uuid) {
        /*QArticle article = QArticle.article;
        QComment comment = QComment.comment;
        BooleanExpression eq = article.uuid.eq(uuid);
        List<ArticleDetailRes> fetch = jpaQueryFactory
                .select(Projections.constructor(ArticleDetailRes.class,
                        article.uuid,
                        article.articleEssential.title,
                        article.articleEssential.contents,
                        article.member.username,
                        article.articleEssential.postDateTime,
                        Projections.list(Projections.constructor(CommentDTO.class,
                                comment.uuid,
                                comment.contents,
                                comment.postDateTime,
                                comment.member.username
                        ))
                ))
                .from(article)
                .leftJoin(article.comments, comment)
                .where(eq)
                .fetch();

        System.out.println(fetch.size());*/
        Article article = articleRepository.findByUuid(uuid).orElseThrow(ArticleNotFoundException::new);
        return new ArticleDetailRes(article);
    }
}
