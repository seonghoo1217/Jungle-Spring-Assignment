package swjungle.week13.assignment.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import swjungle.week13.assignment.application.service.MemberCommandService;
import swjungle.week13.assignment.application.service.MemberQueryService;
import swjungle.week13.assignment.global.dto.ResponseEnvelope;
import swjungle.week13.assignment.presentation.dto.request.SignupReq;
import swjungle.week13.assignment.presentation.dto.response.MemberDetailsRes;
import swjungle.week13.assignment.presentation.dto.response.MemberIdRes;
import swjungle.week13.assignment.presentation.dto.response.SignupRes;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEnvelope<MemberIdRes> signup(@Valid @RequestBody SignupReq signupReq) {
        Long memberId = memberCommandService.signUp(signupReq.username(), signupReq.password());
        return ResponseEnvelope.of(new MemberIdRes(memberId));
    }

    @GetMapping("/signin")
    public ResponseEntity<ResponseEnvelope<MemberDetailsRes>> signin(@RequestParam("username") String username,
                                                                     @RequestParam("password") String password) {
        SignupRes signinRes = memberQueryService.signin(username, password);
        MemberDetailsRes memberDetailsRes = new MemberDetailsRes(signinRes);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", signinRes.accessToken());
        httpHeaders.set("Authorization_Refresh", signinRes.refreshToken());
        return ResponseEntity.status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(ResponseEnvelope.of(memberDetailsRes));
    }

}
