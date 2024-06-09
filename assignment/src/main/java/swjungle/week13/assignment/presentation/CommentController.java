package swjungle.week13.assignment.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import swjungle.week13.assignment.application.service.CommentCommandService;
import swjungle.week13.assignment.domain.Comment;
import swjungle.week13.assignment.global.dto.ResponseEnvelope;
import swjungle.week13.assignment.presentation.dto.request.CreateCommentReq;
import swjungle.week13.assignment.presentation.dto.request.ModifyCommentReq;
import swjungle.week13.assignment.presentation.dto.response.CommentDetailRes;

@RestController
@RequiredArgsConstructor
@RequestMapping("comments")
public class CommentController {
    private final CommentCommandService commentCommandService;

    @PostMapping
    public ResponseEnvelope<?> applyCommentToArticle(@Valid @RequestBody CreateCommentReq createCommentReq, HttpServletRequest servletRequest) {
        String authorization = servletRequest.getHeader("Authorization");
        Comment comment = commentCommandService.applyCommentToArticle(createCommentReq.articleUuid(), createCommentReq.contents(), authorization);

        return ResponseEnvelope.of(new CommentDetailRes(comment));
    }

    @PatchMapping
    public ResponseEnvelope<?> modifyComment(@Valid @RequestBody ModifyCommentReq modifyCommentReq, HttpServletRequest servletRequest) {
        String authorization = servletRequest.getHeader("Authorization");
        Comment comment = commentCommandService.modifyComment(modifyCommentReq.commentUuid(), modifyCommentReq.contents(), authorization);

        return ResponseEnvelope.of(new CommentDetailRes(comment));


    }

}
