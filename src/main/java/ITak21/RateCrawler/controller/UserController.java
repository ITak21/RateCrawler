package ITak21.RateCrawler.controller;

import ITak21.RateCrawler.dto.UserDTO;
import ITak21.RateCrawler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/join")
    public String join(@RequestBody UserDTO userDTO) {
        return userService.join(userDTO);
    }


}
