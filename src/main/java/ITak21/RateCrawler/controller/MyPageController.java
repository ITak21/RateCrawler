package ITak21.RateCrawler.controller;

import ITak21.RateCrawler.dto.FavoriteCompanyDTO;
import ITak21.RateCrawler.service.FavoriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping
    public String myPage(HttpSession session,
                         @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null || userId.equals("anonymousUser")) {
            return "redirect:/login";
        }

        Pageable pageable = PageRequest.of(page, 4); // 페이지 당 4개의 항목
        Page<FavoriteCompanyDTO> favoriteCompanies = favoriteService.getFavoriteCompanies(userId, pageable);
        model.addAttribute("favoriteCompanies", favoriteCompanies);
        return "mypage";
    }

    @PostMapping("/delete")
    public String deleteFavorite(@RequestParam(name = "companyName") String companyName, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null && !userId.equals("anonymousUser")) {
            favoriteService.deleteFavorite(userId, companyName);
        }
        return "redirect:/mypage";
    }
}
