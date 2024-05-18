package ITak21.RateCrawler.controller;

import ITak21.RateCrawler.dto.CompanyInfoDTO;
import ITak21.RateCrawler.service.CrawlerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CrawlerController {
    @Autowired
    private CrawlerService crawlerService;

    @GetMapping("/")
    public String index(HttpSession session) {
        // 세션에 userId가 없을 경우 기본값 설정 (예시)
        if (session.getAttribute("userId") == null) {
            session.setAttribute("userId", "Anonymous");
        }
        return "index";
    }
    @GetMapping("/join")
    public String joinPage() {
        return "join";
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
