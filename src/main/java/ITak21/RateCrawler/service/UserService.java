package ITak21.RateCrawler.service;

import ITak21.RateCrawler.dto.UserDTO;
import ITak21.RateCrawler.entity.UsersEntity;
import ITak21.RateCrawler.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    private UsersRepository userRepository;

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
        return "회원가입 성공";
    }

}
