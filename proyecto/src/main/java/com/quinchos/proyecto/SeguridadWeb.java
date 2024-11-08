package com.quinchos.proyecto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadWeb {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()  // Permitir acceso sin autenticación a /login y recursos estáticos
                .requestMatchers("/admin/**").hasRole("ADMIN") // Proteger rutas de administrador
                .anyRequest().authenticated()  // Requiere autenticación para otras rutas
            )
            .formLogin((form) -> form
                .loginPage("/login")  // Página de inicio de sesión personalizada
                .loginProcessingUrl("/logincheck")  // URL para procesar el login
                .usernameParameter("email")  // Parámetro de nombre de usuario (puedes cambiarlo según tu formulario)
                .passwordParameter("password")  // Parámetro de contraseña
                .defaultSuccessUrl("/", true)  // Redirigir al inicio tras un inicio de sesión exitoso
                .failureUrl("/login?error=true")  // Redirigir a login con error si la autenticación falla
                .permitAll()  // Permitir acceso a la página de login sin autenticación
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")  // URL para cerrar sesión
                .logoutSuccessUrl("/")  // Redirigir al inicio tras cerrar sesión
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());  // Deshabilitar CSRF (solo si es necesario, ten cuidado con esto)

        return http.build();
    }
}
