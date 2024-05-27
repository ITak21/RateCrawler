package ITak21.RateCrawler.controller;

import ITak21.RateCrawler.dto.UserDTO;
import ITak21.RateCrawler.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/join")
    public String join(@RequestBody UserDTO userDTO) {
        return userService.join(userDTO);
    }
    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();  // 세션 무효화
        response.sendRedirect("/");  // 루트 페이지로 리디렉션
    }


}
