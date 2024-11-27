package cs.skku.edu.mrdang.security.jwt;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RefreshToken {

    @Id
    private String token;

    private String glsId;

    public static RefreshToken of(String token, String glsId) {
        return RefreshToken.builder()
                .token(token)
                .glsId(glsId)
                .build();
    }
}
