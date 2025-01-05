package cs.skku.edu.mrdang.domain.user.controller;

import cs.skku.edu.mrdang.domain.user.dto.UserDTO;
import cs.skku.edu.mrdang.domain.user.entity.User;
import cs.skku.edu.mrdang.domain.user.service.UserService;
import cs.skku.edu.mrdang.security.annotation.Auth;
import cs.skku.edu.mrdang.security.annotation.MasterOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @MasterOnly
    @GetMapping()
    public ResponseEntity<List<UserDTO.Response>> getUsers() {
        List<UserDTO.Response> response = userService.getUsers();
        return ResponseEntity.ok().body(response);
    }

    @MasterOnly
    @PatchMapping()
    public ResponseEntity<UserDTO.Response> changeRole(
            @Auth User user,
            @RequestBody UserDTO.ChangeRoleRequest request
    ) {
        UserDTO.Response response =  userService.changeRole(request);
        return ResponseEntity.ok().body(response);
    }
}
