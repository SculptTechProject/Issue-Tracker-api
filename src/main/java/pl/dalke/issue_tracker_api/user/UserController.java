package pl.dalke.issue_tracker_api.user;

import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //TODO, after auth implementation :D
}
