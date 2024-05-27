package ITak21.RateCrawler.controller;

import ITak21.RateCrawler.dto.CompanyInfoDTO;
import ITak21.RateCrawler.service.CrawlerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CrawlerController {
    @Autowired
    private CrawlerService crawlerService;

    @GetMapping("/")
    public String index(HttpSession session,Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            String username;
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            session.setAttribute("userId", username);
            model.addAttribute("username", username);
            System.out.println(username);
        }
        return "index";
    }
    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/crawl")
    public String crawl(@RequestParam("company") String company, HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");

        CompanyInfoDTO companyInfoDTO = crawlerService.fetchCompanyInfo(company,userId);
        String star2 = crawlerService.fetchStar2(company);
        model.addAttribute("companyInfo", companyInfoDTO);
        model.addAttribute("star2", star2);
        model.addAttribute("company", company);

        return "result";
    }
}
