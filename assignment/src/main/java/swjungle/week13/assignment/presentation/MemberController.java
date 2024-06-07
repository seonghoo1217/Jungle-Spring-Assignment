package swjungle.week13.assignment.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import swjungle.week13.assignment.application.service.MemberCommandService;
import swjungle.week13.assignment.global.dto.ResponseEnvelope;
import swjungle.week13.assignment.presentation.dto.request.MemberCreateReq;
import swjungle.week13.assignment.presentation.dto.response.MemberIdRes;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberCommandService memberCommandService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEnvelope<MemberIdRes> signup(@Valid @RequestBody MemberCreateReq memberCreateReq) {
        Long memberId = memberCommandService.signUp(memberCreateReq.username(), memberCreateReq.password());
        return ResponseEnvelope.of(new MemberIdRes(memberId));
    }
}
