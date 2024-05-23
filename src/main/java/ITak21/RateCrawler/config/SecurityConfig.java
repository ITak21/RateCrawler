package ITak21.RateCrawler.config;

import ITak21.RateCrawler.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/join", "/api/join", "/css/**", "/js/**","/**").permitAll() // 회원가입 경로와 리소스 경로 허용
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
                )
                .formLogin((form) -> form
                        .loginPage("/login") // 커스텀 로그인 페이지 설정
                        .defaultSuccessUrl("/?loginSuccess=true", true) // 로그인 성공 시 메인 페이지로 리디렉션
                        .failureUrl("/login?error=true")
                        .permitAll() // 로그인 페이지 접근 허용
                )
                .logout((logout) -> logout
                        .permitAll() // 로그아웃은 누구나 접근 가능
                )
                .csrf(csrf -> csrf.disable()); // 필요시 CSRF 보호 비활성화
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
