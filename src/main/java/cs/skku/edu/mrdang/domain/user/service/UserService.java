package cs.skku.edu.mrdang.domain.user.service;

import cs.skku.edu.mrdang.security.sso.SSOUser;
import cs.skku.edu.mrdang.domain.user.dto.UserDTO;
import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.domain.user.repository.UserRepository;
import cs.skku.edu.mrdang.exception.ErrorCode;
import cs.skku.edu.mrdang.exception.RestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDTO.Response> getUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO.Response::from)
                .toList();
    }

    public UserDTO.Response changeRole(UserDTO.ChangeRoleRequest request) {
        User user = getUserByGlsId(request.getGlsId());
        user.setRole(request.getRole());

        userRepository.save(user);
        return UserDTO.Response.from(user);
    }

    @Transactional(readOnly = true)
    public User getUserByGlsId(String glsId) {
        return userRepository.findByGlsId(glsId)
                .orElseThrow(() -> new RestException(ErrorCode.USER_NOT_FOUND));
    }

    public User getOrCreateUser(SSOUser ssoUser) {
        User user = userRepository.findByGlsId(ssoUser.getUserId())
                .orElseGet(() -> this.createUser(ssoUser));

        return user;
    }


    private User createUser(SSOUser ssoUser) {
        User user = User.from(ssoUser);

        User saved = userRepository.save(user);
        return saved;
    }
}
