package ITak21.RateCrawler.repository;

import ITak21.RateCrawler.dto.FavoriteCompanyDTO;
import ITak21.RateCrawler.entity.FavoriteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
    @Query("SELECT new ITak21.RateCrawler.dto.FavoriteCompanyDTO(f.favName, s.searchCode) " +
            "FROM FavoriteEntity f LEFT JOIN SearchEntity s ON f.favName = s.searchName " +
            "WHERE f.userId = :userId GROUP BY f.favName, s.searchCode")
    Page<FavoriteCompanyDTO> findFavoriteCompanies(@Param("userId") String userId, Pageable pageable);

    void deleteByUserIdAndFavName(String userId, String favName);
}
