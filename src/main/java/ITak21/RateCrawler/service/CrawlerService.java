package ITak21.RateCrawler.service;

import ITak21.RateCrawler.dto.CompanyInfoDTO;
import ITak21.RateCrawler.entity.SearchEntity;
import ITak21.RateCrawler.repository.SearchRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class CrawlerService {
    private static final Logger logger = LoggerFactory.getLogger(CrawlerService.class);

    @Autowired

    private SearchRepository searchRepository;


    public CompanyInfoDTO fetchCompanyInfo(String company,String userId) {
        CompanyInfoDTO companyInfoDTO = new CompanyInfoDTO();
        SearchEntity searchEntity = new SearchEntity();

        try {
            String bla = "https://www.teamblind.com/kr/company/";
            Document doc = Jsoup.connect(bla+company).get();

            Elements starElements = doc.select("strong.rate");
            String star=starElements.text().length() > 12 ? starElements.text().substring(12) : "N/A";
            companyInfoDTO.setStar(star);

            Elements linkElements = doc.select("#wrap > section > div > div > div.cpctw.cpovw > div > div > div > section.overview > ul > li:nth-child(1)");
            String link = linkElements.text().isEmpty() ? "No link available" : linkElements.text();
            companyInfoDTO.setLink(link);

            Elements categoryElements = doc.select("#wrap > section > div > div > div.cpctw.cpovw > div > div > div > section.overview > ul > li:nth-child(2)");
            String category = categoryElements.text().isEmpty() ? "No category available" : categoryElements.text();
            companyInfoDTO.setCategory(category);

            Elements locationElements = doc.select("#wrap > section > div > div > div.cpctw.cpovw > div > div > div > section.overview > ul > li:nth-child(3)");
            String location = locationElements.text().isEmpty() ? "No location available" : locationElements.text();
            companyInfoDTO.setLocation(location);

            Elements aboutElements = doc.select("#wrap > section > div > div > div.cpctw.cpovw > div > div > div > section.overview > div > p");
            String about = aboutElements.text().isEmpty() ? "No about available" : aboutElements.text();
            companyInfoDTO.setAbout(about);

            if(userId.isEmpty()){userId="Anonymous";}
            searchEntity.setUserId(userId);
            searchEntity.setSearchName(company);
            searchEntity.setSearchCode(category.substring(3));
            searchEntity.setSearchDate(LocalDateTime.now());

            if (!star.equals("N/A") && !link.equals("No link available") && !category.equals("No category available") && !location.equals("No location available") && !about.equals("No about available")) {
                searchRepository.save(searchEntity);
                logger.info("Data saved for company: {}", company);
            } else {
                companyInfoDTO.setError("블라인드에 유효한 정보가 없습니다.");
                logger.warn("No valid data found for company: {}", company);
            }


        } catch (IOException e) {
            logger.error("Error fetching data for company: {}", company, e);
            companyInfoDTO.setError("블라인드에 정보가 없습니다.");

        }
        return companyInfoDTO;
    }

    public String fetchStar2(String company) {
        try {
            String jobple = "https://www.google.com/search?q=잡플래닛+";
            Document doc2 = Jsoup.connect(jobple + company).get();
            Elements elements = doc2.select(".fG8Fp.uo4vr span:nth-of-type(2)");

            if (elements.isEmpty()) {
                // 요소가 존재하지 않는 경우
                logger.warn("No elements found for the specified selector.");
                return "잡플래닛에 정보가 없습니다.";
            }

            String elementText = elements.text();

            if (elementText.length() < 7) {
                // 텍스트 길이가 충분하지 않은 경우
                logger.warn("Element text is too short: {}", elementText);
                return "잡플래닛에 정보가 없습니다.";
            }

            String star2 = elementText.substring(4, 7);
            return star2;
        } catch (IOException e) {
            logger.error("Error fetching data from 잡플래닛 for company: {}", company, e);
            return "잡플래닛에 정보가 없습니다.";
        }
    }

}
