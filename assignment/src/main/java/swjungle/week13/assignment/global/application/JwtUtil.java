package swjungle.week13.assignment.global.application;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import swjungle.week13.assignment.domain.Member;
import swjungle.week13.assignment.domain.repo.MemberRepository;
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

    public String refreshAccessToken(Member member) {
        return createToken(member, jwtProvider.getRefresh_expiration());
    }

    /*public String reissue(String refreshToken){

    }*/

    public String getClaimUsername(String token) {
        return getDecodeJwt(token).getClaim("username").asString();
    }

    public String createToken(Member member, long expireTime) {
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
}