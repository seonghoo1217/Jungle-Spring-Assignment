package swjungle.week13.assignment.application.impl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swjungle.week13.assignment.application.dto.ArticleDTO;
import swjungle.week13.assignment.application.dto.CommentDTO;
import swjungle.week13.assignment.application.service.ArticleQueryService;
import swjungle.week13.assignment.domain.Article;
import swjungle.week13.assignment.domain.QArticle;
import swjungle.week13.assignment.domain.QComment;
import swjungle.week13.assignment.domain.exception.ArticleNotFoundException;
import swjungle.week13.assignment.domain.repo.ArticleRepository;
import swjungle.week13.assignment.global.dto.CustomPageable;
import swjungle.week13.assignment.presentation.dto.response.ArticleDetailRes;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleQueryServiceImpl implements ArticleQueryService {
    private final JPAQueryFactory jpaQueryFactory;
    private final ArticleRepository articleRepository;

    @Override
    public Page<ArticleDTO> findAllArticles(int page, int size) {
        QArticle article = QArticle.article;
        QComment comment = QComment.comment;
        Pageable pageable = CustomPageable.of(page, size);
        List<ArticleDTO> content = jpaQueryFactory
                .select(Projections.constructor(ArticleDTO.class,
                        article.uuid,
                        article.articleEssential.title,
                        article.articleEssential.contents,
                        article.member.username,
                        article.articleEssential.postDateTime,
                        Projections.list(Projections.constructor(CommentDTO.class,
                                comment.uuid,
                                comment.contents,
                                comment.postDateTime,
                                comment.member.username))
                ))
                .from(article)
                .leftJoin(article.comments, comment)
                .orderBy(article.articleEssential.postDateTime.desc(), comment.postDateTime.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long totalCount = jpaQueryFactory
                .select(article.count())
                .from(article)
                .fetchOne();
        return new PageImpl<>(content, pageable, totalCount);
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
