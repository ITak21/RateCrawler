package ITak21.RateCrawler.controller;

import ITak21.RateCrawler.dto.UserDTO;
import ITak21.RateCrawler.service.FavoriteService;
import ITak21.RateCrawler.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FavoriteService favoriteService;



    @PostMapping("/join")
    public String join(@RequestBody UserDTO userDTO) {
        return userService.join(userDTO);
    }
    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) throws IOException {
        session.invalidate();  // 세션 무효화
        response.sendRedirect("/");  // 루트 페이지로 리디렉션
    }

    @PostMapping("/addFavorite")
    public String addFavorite(@RequestParam("companyName") String companyName, HttpSession session) {
        String userId= (String) session.getAttribute("userId");
        favoriteService.addFavorite(userId, companyName);
        return "Success";
    }
    @PostMapping("/checkPassword")
    public ResponseEntity<Void> checkPassword(@RequestBody UserDTO userDTO, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        boolean isValid = userService.checkPassword(userId, userDTO.getPassword());
        if (isValid) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/deleteAccount")
    public boolean deleteAccount(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        session.invalidate();  // 세션 무효화
        return userService.deleteAccount(userId);
    }

}
