package ac.projects.cablelo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(customizer->customizer.disable())
                .authorizeHttpRequests(auth-> auth.requestMatchers("/drivers/**")
                        .hasRole("ADMIN")
                        .requestMatchers("/users/**").hasAnyRole("USER","ADMIN").anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();



    }


}
