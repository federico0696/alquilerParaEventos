package com.quinchos.proyecto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;



@Configuration
@EnableWebSecurity
public class SeguridadWeb {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/admin/**").hasRole("ADMIN")  // Solo accesible por ADMIN
                        .requestMatchers("/propietario/**").hasRole("PROPIETARIO")  // Solo accesible por PROPIETARIO
                        .requestMatchers("/inquilino/**").hasRole("INQUILINO")  // Solo accesible por INQUILINO
                        .requestMatchers("/**").permitAll()
                )    
                        .formLogin((form) -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/logincheck")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true) //inicio de sesion exitoso
                        .failureUrl("/login?error=true") // Aquí defines la URL en caso de fallo
                        .permitAll()
                                        .successHandler((request, response, authentication) -> {
                                            // Obtener el rol del usuario autenticado
                                            String role = authentication.getAuthorities().stream()
                                                    .map(GrantedAuthority::getAuthority)
                                                    .filter(r -> r.startsWith("ROLE_"))
                                                    .map(r -> r.substring(5)) // Eliminar el prefijo "ROLE_"
                                                    .findFirst()
                                                    .orElse("INQUILINO"); // Si no tiene rol, lo redirigimos como inquilino por defecto

                                            // Redirigir según el rol del usuario
                                            if ("ADMIN".equals(role)) {
                                                response.sendRedirect("/admin/usuarios"); // Redirige a la página de administración
                                            } else if ("PROPIETARIO".equals(role)) {
                                                response.sendRedirect("/menu"); // Redirige a la página de propietario
                                            } else {
                                                response.sendRedirect("/menu"); // Redirige a la página de inquilino
                                            }
                                        })
                        )
                        .logout((logout) -> logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/")
                            .permitAll()
                        )         
                        .csrf(csrf -> csrf.disable());
        return http.build();
            
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}