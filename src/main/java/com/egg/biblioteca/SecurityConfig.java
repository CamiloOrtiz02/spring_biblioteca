package com.egg.biblioteca;

import com.egg.biblioteca.Service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioServicio us;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(us)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Deshabilita CSRF si es necesario
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN") // 🔥 Permitir el acceso a /admin/** solo para usuarios con el rol ADMIN
                        .requestMatchers("/registrar", "/login").permitAll() // 🔥 Permitir el acceso a /registrar y /login sin autenticación
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll() // 🔥 Permitir archivos estáticos sin autenticación
                        .anyRequest().authenticated() // 🔥 Cualquier otra peticion debe ser autenticada
                )
                .formLogin(form -> form
                        .loginPage("/login") // 🔥 Configurar la URL de inicio de sesión
                        .loginProcessingUrl("/logincheck") // 🔥 Configurar la URL donde se envia y procesa los datos del login
                        .usernameParameter("email") // 🔥 Configurar el nombre de parámetro para el email
                        .passwordParameter("password") // 🔥 Configurar el nombre de parámetro para la clave
                        .defaultSuccessUrl("/inicio") // 🔥 Configurar la URL de redirección en caso de inicio de sesión exitoso
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 🔥 Configurar la URL de cierre de sesión
                        .logoutSuccessUrl("/login") // 🔥 Configurar la URL de redirección en caso de cierre de sesión exitoso
                        .permitAll()
                )
                .build();
    }
}
