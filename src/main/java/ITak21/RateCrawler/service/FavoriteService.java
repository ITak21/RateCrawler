package ITak21.RateCrawler.service;

import ITak21.RateCrawler.dto.FavoriteCompanyDTO;
import ITak21.RateCrawler.repository.FavoriteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    public Page<FavoriteCompanyDTO> getFavoriteCompanies(String userId, Pageable pageable) {
        return favoriteRepository.findFavoriteCompanies(userId, pageable);
    }
    @Transactional
    public void deleteFavorite(String userId, String favName) {
        favoriteRepository.deleteByUserIdAndFavName(userId, favName);
    }
}
