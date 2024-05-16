package ITak21.RateCrawler.controller;

import ITak21.RateCrawler.service.CrawlerService;
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
    public String index() {
        return "index";
    }

    @GetMapping("/crawl")
    public String crawl(@RequestParam("company") String company, Model model) {
        String star = crawlerService.fetchStar(company);
        String star2 = crawlerService.fetchStar2(company);
        model.addAttribute("star", star);
        model.addAttribute("star2", star2);
        model.addAttribute("company", company);

        return "result";
    }
}
