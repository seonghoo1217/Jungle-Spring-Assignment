package swjungle.week13.assignment.global.application;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swjungle.week13.assignment.domain.Member;
import swjungle.week13.assignment.domain.MemberAuth;
import swjungle.week13.assignment.domain.exception.MemberNotFoundException;
import swjungle.week13.assignment.domain.repo.MemberRepository;
import swjungle.week13.assignment.global.application.dto.ReissueToken;
import swjungle.week13.assignment.global.dto.JwtProvider;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    public String createAccessToken(Member member) {
        return createToken(member, jwtProvider.getAccess_expiration());
    }

    public String createRefreshToken(Member member) {
        return createToken(member, jwtProvider.getRefresh_expiration());
    }

    public ReissueToken reissue(String refreshToken) {
        Member member = memberRepository.findByRefreshToken(refreshToken).orElseThrow(MemberNotFoundException::new);

        String reissueAccessToken = createAccessToken(member);
        String reissueRefreshToken = createRefreshToken(member);
        member.updateRefreshToken(reissueRefreshToken);
        return new ReissueToken(reissueAccessToken, reissueRefreshToken);
    }

    public String getClaimUsername(String token) {
        return getDecodeJwt(token).getClaim("username").asString();
    }

    private String createToken(Member member, long expireTime) {
        return JWT.create()
                .withSubject(member.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                .withClaim("username", member.getUsername())
                .sign(Algorithm.HMAC512(jwtProvider.getSecretKey()));
    }

    //TODO 회원 Repository Layer 연결
    public boolean isValidateToken(String token) {
//        if(memberRepository.findByUsername(getDecodeJwt(token).getClaim("username").asString()).orElseThrow(()->new RuntimeException){
        String username = getDecodeJwt(token).getClaim("username").asString();
        Optional<Member> findMember = memberRepository.findByUsername(username);
        if (findMember.isPresent()) {
            return !isExpired(token);
        }
        return false;
    }

    private boolean isExpired(String token) {
        Date expireDate = getDecodeJwt(token).getExpiresAt();
        return expireDate.before(new Date());
    }

    private DecodedJWT getDecodeJwt(String token) {
        return JWT.require(Algorithm.HMAC512(jwtProvider.getSecretKey())).build().verify(token);
    }

    public boolean isOwner(String authorization, Long memberId) {
        Member member = findMemberByAuthorization(authorization);

        return member.getId().equals(memberId);
    }

    public boolean isAdmin(String authorization) {
        Member member = findMemberByAuthorization(authorization);
        return member.getMemberAuth().equals(MemberAuth.ADMIN);
    }

    public String getUsernameByClaim(String authorization) {
        String accessToken = authorization.substring(7);
        return getDecodeJwt(accessToken).getClaim("username").asString();
    }

    private Member findMemberByAuthorization(String authorization) {
        String username = getUsernameByClaim(authorization);
        return memberRepository.findByUsername(username).orElseThrow(MemberNotFoundException::new);
    }
}
