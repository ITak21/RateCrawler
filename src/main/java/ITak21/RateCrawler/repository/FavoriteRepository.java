package ITak21.RateCrawler.repository;

import ITak21.RateCrawler.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Integer> {
}
