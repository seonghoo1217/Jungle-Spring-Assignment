package swjungle.week13.assignment.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swjungle.week13.assignment.application.service.ArticleCommandService;
import swjungle.week13.assignment.domain.Article;
import swjungle.week13.assignment.domain.ArticleEssential;
import swjungle.week13.assignment.domain.Member;
import swjungle.week13.assignment.domain.exception.MemberNotFoundException;
import swjungle.week13.assignment.domain.repo.ArticleRepository;
import swjungle.week13.assignment.domain.repo.MemberRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleCommandServiceImpl implements ArticleCommandService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Override
    public Article createArticle(String title, String contents, String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);
        Article article = new Article(UUID.randomUUID(), new ArticleEssential(title, contents, LocalDateTime.now()), member);

        return articleRepository.save(article);
    }
}
