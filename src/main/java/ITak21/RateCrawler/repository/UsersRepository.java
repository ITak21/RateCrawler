package ITak21.RateCrawler.repository;

import ITak21.RateCrawler.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<UsersEntity, String> {

}
