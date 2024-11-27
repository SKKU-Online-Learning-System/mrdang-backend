package cs.skku.edu.mrdang.security.jwt;

import cs.skku.edu.mrdang.exception.ErrorCode;
import cs.skku.edu.mrdang.exception.RestException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey secretKey;
    private final Long accessTokenExpiration;
    private final Long refreshTokenExpiration;
    private final RefreshTokenRepository refreshTokenRepository;

    public JWTUtil(
            @Value("${spring.auth.jwt.secret-key}") String secretKey,
            @Value("${spring.auth.jwt.access-token-expiry}") Long accessTokenExpiration,
            @Value("${spring.auth.jwt.refresh-token-expiry}") Long refreshTokenExpiration,
            RefreshTokenRepository refreshTokenRepository) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Transactional
    public UserToken createUserToken(String glsId) {
        String accessToken = createToken(glsId, accessTokenExpiration);
        String refreshToken = createToken("", refreshTokenExpiration);

        saveRefreshToken(refreshToken, glsId);

        return UserToken.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String regenerateAccessToken(String glsId) {
        return createToken(glsId, accessTokenExpiration);
    }

    @Transactional(readOnly = true)
    public RefreshToken getRefreshToken(final String token) {
        return refreshTokenRepository.findById(token)
                .orElseThrow(() -> new RestException(ErrorCode.INVALID_REFRESH_TOKEN));
    }


    @Transactional
    public void removeRefreshToken(String token)  {
        refreshTokenRepository.deleteById(token);
    }


    public void validateAccessToken(final String token) {
        try {
            parse(token);
        } catch (final ExpiredJwtException e) {
            throw new RestException(ErrorCode.EXPIRED_PERIOD_ACCESS_TOKEN);
        } catch (final JwtException | IllegalArgumentException e) {
            throw new RestException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    public void validateRefreshToken(final String token) {
        try {
            parse(token);
        } catch (final ExpiredJwtException e) {
            throw new RestException(ErrorCode.EXPIRED_PERIOD_REFRESH_TOKEN);
        } catch (final JwtException | IllegalArgumentException e) {
            throw new RestException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    public boolean isAccessTokenExpired(final String token) {
        try {
            parse(token);
        } catch (final ExpiredJwtException e) {
            return true;
        } catch (final JwtException | IllegalArgumentException e) {
            return false;
        }

        return false;
    }


    public String getSubject(String token) {
       return parse(token).getPayload().getSubject();
    }

    private Jws<Claims> parse(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token);
    }

    private String createToken(String glsId, Long expired) {
        final Date now = new Date();
        final Date expiredDate = new Date(now.getTime() + expired);
        return Jwts.builder()
                .issuer("SSA Mrdang+")
                .subject(glsId)
                .issuedAt(now)
                .expiration(expiredDate)
                .signWith(secretKey)
                .compact();
    }

    private void saveRefreshToken(String refreshToken, String glsId) {
        RefreshToken toSave = RefreshToken.of(refreshToken, glsId);
        refreshTokenRepository.save(toSave);
    }
}
