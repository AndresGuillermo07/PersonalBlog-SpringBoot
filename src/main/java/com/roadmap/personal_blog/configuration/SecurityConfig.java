package com.roadmap.personal_blog.configuration;

import com.roadmap.personal_blog.services.UserDetailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
                .logout(logout -> logout.deleteCookies("JSESSIONID"))
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                )
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(http -> {

                    // PUBLIC ENDPOINTS
                    http.requestMatchers(HttpMethod.GET,"/").authenticated();
                    http.requestMatchers(HttpMethod.GET,"/home").authenticated();
                    http.requestMatchers(HttpMethod.GET,"/article/{idPost}").authenticated();
                    http.requestMatchers(HttpMethod.GET,"/about").authenticated();
                    http.requestMatchers(HttpMethod.GET,"/contact").authenticated();

                    // PRIVATE ENDPOINTS

                    http.requestMatchers(HttpMethod.GET,"/admin").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.PUT,"/edit/{idPost}").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.GET,"/new").hasRole("ADMIN");
                    http.requestMatchers(HttpMethod.POST,"/new").hasRole("ADMIN");


                    http.requestMatchers("/css/**", "/js/**", "/images/**").permitAll().anyRequest().authenticated();

                })
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
