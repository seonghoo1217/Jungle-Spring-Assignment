package swjungle.week13.assignment.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swjungle.week13.assignment.application.service.ArticleCommandService;
import swjungle.week13.assignment.domain.Article;
import swjungle.week13.assignment.domain.ArticleEssential;
import swjungle.week13.assignment.domain.Member;
import swjungle.week13.assignment.domain.exception.ArticleNotFoundException;
import swjungle.week13.assignment.domain.exception.ArticleNotOwnerException;
import swjungle.week13.assignment.domain.exception.MemberNotFoundException;
import swjungle.week13.assignment.domain.repo.ArticleRepository;
import swjungle.week13.assignment.domain.repo.MemberRepository;
import swjungle.week13.assignment.global.application.JwtUtil;
import swjungle.week13.assignment.presentation.dto.response.ArticleDetailRes;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;

    @Override
    public Article createArticle(String title, String contents, String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);
        Article article = new Article(UUID.randomUUID(), new ArticleEssential(title, contents, LocalDateTime.now()), member);

        return articleRepository.save(article);
    }

    @Override
    public ArticleDetailRes modifyArticleEssential(String authorization, UUID uuid, String title, String contents) {
        Article article = articleRepository.findByUuid(uuid).orElseThrow(ArticleNotFoundException::new);
        if (!jwtUtil.isOwner(authorization, article.getMember().getId()) || !jwtUtil.isAdmin(authorization)) {
            throw new ArticleNotOwnerException();
        }
        article.modifyArticleEssential(title, contents);
        return new ArticleDetailRes(article);
    }

    @Override
    public void deleteArticle(String authorization, UUID uuid) {
        Article article = articleRepository.findByUuid(uuid).orElseThrow(ArticleNotFoundException::new);
        if (!jwtUtil.isOwner(authorization, article.getMember().getId()) || !jwtUtil.isAdmin(authorization)) {
            throw new ArticleNotOwnerException();
        }
        articleRepository.delete(article);
    }
}
