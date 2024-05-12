package ITak21.RateCrawler;

import ITak21.RateCrawler.entity.FavoriteEntity;
import ITak21.RateCrawler.entity.SearchEntity;
import ITak21.RateCrawler.entity.UsersEntity;
import ITak21.RateCrawler.repository.FavoriteRepository;
import ITak21.RateCrawler.repository.SearchRepository;
import ITak21.RateCrawler.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.springframework.test.util.AssertionErrors.assertNotNull;


@SpringBootTest
@Transactional
class RateCrawlerApplicationTests {

	@Autowired
//	private SearchRepository searchRepository;
	private FavoriteRepository favoriteRepository;

	@Test
	/* favorite테이블 테스트 코드 */
	public void testInsertFavorite() {
		FavoriteEntity favorite = new FavoriteEntity();
		favorite.setUserId("user123");
		favorite.setFavName("Test");


		FavoriteEntity savedFavorite = favoriteRepository.save(favorite);

		assertNotNull( Integer.toString(savedFavorite.getFavId()), "Search ID should not be null after saving");
		System.out.println("Saved favorite ID: " + savedFavorite.getFavId());

	}

	/* search테이블 테스트 코드 */
//	public void testInsertSearch() {
//		SearchEntity search = new SearchEntity();
//		search.setUserId("user123");
//		search.setSearchName("Test Search");
//		search.setSearchCode("TS01");
//		search.setSearchDate(LocalDateTime.now());
//
//		SearchEntity savedSearch = searchRepository.save(search);
//
//		assertNotNull( Integer.toString(savedSearch.getSearchId()), "Search ID should not be null after saving");
//		System.out.println("Saved search ID: " + savedSearch.getSearchId());
//
//	}




}


