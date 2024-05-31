package ITak21.RateCrawler.service;

import ITak21.RateCrawler.dto.UserDTO;
import ITak21.RateCrawler.entity.UsersEntity;
import ITak21.RateCrawler.repository.FavoriteRepository;
import ITak21.RateCrawler.repository.UsersRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String join(UserDTO userDTO) {
        // 비밀번호 해시화
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

        // UsersEntity 객체 생성 및 저장
        UsersEntity user = new UsersEntity();
        user.setId(userDTO.getUserid());
        user.setUsernm(userDTO.getUsername());
        user.setUserpw(hashedPassword);
        user.setUserdate(LocalDateTime.now());
        userRepository.save(user);

        // 회원가입 완료 메시지 반환
        return "회원가입이 완료되었습니다.";
    }
    public boolean checkPassword(String userId, String rawPassword) {
        Optional<UsersEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UsersEntity user = optionalUser.get();
            return passwordEncoder.matches(rawPassword, user.getUserpw());
        }
        return false;
    }
    @Transactional
    public boolean deleteAccount(String userId) {
        if (userRepository.existsById(userId)) {
            // FavoriteEntity 삭제
            favoriteRepository.deleteByUserId(userId);
            // UsersEntity 삭제
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

}
