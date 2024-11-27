package cs.skku.edu.mrdang.domain.user.dto;

import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.domain.user.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CreateRequest {
        private String glsId;
        private UserRole userRole;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ChangeRoleRequest {
        private String glsId;
        private UserRole role;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private String name;
        private String glsId;

        private UserRole role;

        public static Response from(User user){
            return Response.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .glsId(user.getGlsId())
                    .role(user.getRole())
                    .build();
        }
    }
}
