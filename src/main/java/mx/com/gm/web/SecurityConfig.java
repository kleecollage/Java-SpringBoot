package mx.com.gm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    // Dependency injection
    private final UserDetailsService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(@Qualifier("userService") UserDetailsService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/webjars/**", "/css/**", "/js/**", "/images/**").permitAll() // permit access to static resources
                    .requestMatchers("/edit/**", "/add/**", "/delete").hasRole("ADMIN")
                    .requestMatchers("/").hasAnyRole("USER", "ADMIN")
                    .anyRequest().authenticated()
                )
                .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/errors/403")
                );

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    //    @Bean
    //    public InMemoryUserDetailsManager userDetailsService() {
    //        UserDetails admin = User.withDefaultPasswordEncoder()
    //                .username("admin")
    //                .password("admin")
    //                .roles("ADMIN", "USER") // prefix "ROLE_" auto-generated
    //                .build();
    //
    //        UserDetails user = User.withDefaultPasswordEncoder()
    //                .username("user")
    //                .password("user")
    //                .roles("USER")
    //                .build();
    //
    //        return new InMemoryUserDetailsManager(admin, user);
    //    }

}
