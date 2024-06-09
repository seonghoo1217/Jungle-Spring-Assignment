package swjungle.week13.assignment.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import swjungle.week13.assignment.application.service.CommentCommandService;
import swjungle.week13.assignment.domain.Article;
import swjungle.week13.assignment.domain.Comment;
import swjungle.week13.assignment.domain.Member;
import swjungle.week13.assignment.domain.exception.ArticleNotFoundException;
import swjungle.week13.assignment.domain.exception.MemberNotFoundException;
import swjungle.week13.assignment.domain.repo.ArticleRepository;
import swjungle.week13.assignment.domain.repo.CommentRepository;
import swjungle.week13.assignment.domain.repo.MemberRepository;
import swjungle.week13.assignment.global.application.JwtUtil;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentCommandServiceImpl implements CommentCommandService {
    private final ArticleRepository articleRepository;

    private final CommentRepository commentRepository;

    private final MemberRepository memberRepository;

    private final JwtUtil jwtUtil;

    @Override
    public Comment applyCommentToArticle(UUID uuid, String contents, String authorization) {
        String username = jwtUtil.getUsernameByClaim(authorization);

        Article article = articleRepository.findByUuid(uuid).orElseThrow(ArticleNotFoundException::new);
        Member member = memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);

        Comment comment = new Comment(UUID.randomUUID(), contents, LocalDateTime.now(), article, member);
        commentRepository.save(comment);
        return comment;
    }
}
