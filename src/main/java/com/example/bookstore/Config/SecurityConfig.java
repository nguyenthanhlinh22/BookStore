package com.example.bookstore.Config;

import com.example.bookstore.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private CustomUserDetailService userService;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/auth/login", "/auth/register", "/home").permitAll()  // Cho phép truy cập trang login và register
                        .requestMatchers("/groups/**").hasRole("ADMIN")
                        .anyRequest().authenticated()  // Tất cả các yêu cầu khác đều cần xác thực
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")  // Định nghĩa trang login
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/home", true)  // Sau khi đăng nhập thành công, điều hướng tới trang home
                        .failureUrl("/auth/login?error=true")  // Trang login khi có loi xác thực
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")//url thực hiện khi đăng xuất
                        .logoutSuccessUrl("/auth/login")//url đieu hương để khi đăng xất
                        .invalidateHttpSession(true)//vô hiệu hoá cookies
                        .deleteCookies("JSESSIONID")// xoá cookies
                        .permitAll()
                        );


        return http.build();
    }
}

