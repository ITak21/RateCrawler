package ITak21.RateCrawler;

import ITak21.RateCrawler.entity.FavoriteEntity;

import ITak21.RateCrawler.repository.FavoriteRepository;

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

	private FavoriteRepository favoriteRepository;

	@Test

	public void testInsertFavorite() {
		FavoriteEntity favorite = new FavoriteEntity();
		favorite.setUserId("user123");
		favorite.setFavName("Test");


		FavoriteEntity savedFavorite = favoriteRepository.save(favorite);

		assertNotNull( Integer.toString(savedFavorite.getFavId()), "Search ID should not be null after saving");
		System.out.println("Saved favorite ID: " + savedFavorite.getFavId());

	}
}


