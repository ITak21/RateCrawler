package ITak21.RateCrawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CrawlerService {
    public String fetchStar(String company) {
        try {
            String bla = "https://www.teamblind.com/kr/company/";
            Document doc = Jsoup.connect(bla+company).get();
            Elements elements = doc.select("strong.rate");
            String star=elements.text().substring(12);

            return star;
        } catch (IOException e) {
            e.printStackTrace();
            return "블라인드에 정보가 없습니다.";
        }
    }
    public String fetchStar2(String company) {
        try {
            String jobple = "https://www.google.com/search?q=잡플래닛+";
            Document doc2 = Jsoup.connect(jobple+company).get();
            Elements elements = doc2.select(".fG8Fp.uo4vr span:nth-child(2)");
            String star2=elements.text().substring(4,8);

            return star2;
        } catch (IOException e) {
            e.printStackTrace();
            return "잡플래닛에 정보가 없습니다.";
        }
    }

}
