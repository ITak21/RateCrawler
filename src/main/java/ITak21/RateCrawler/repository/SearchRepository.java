package ITak21.RateCrawler.repository;

import ITak21.RateCrawler.entity.SearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchRepository extends JpaRepository<SearchEntity, Integer> {
}
