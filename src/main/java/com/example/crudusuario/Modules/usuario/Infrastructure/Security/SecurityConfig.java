package com.example.crudusuario.Modules.usuario.Infrastructure.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita CSRF (essencial para testes via Postman/Insomnia)
                .csrf(AbstractHttpConfigurer::disable)
                // Desabilita tela de login padrão e popup de autenticação básica
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                // Configura permissões de rota
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuario/**", "/usuario").permitAll() // Cobre /usuario, /usuario/get, /usuario/post, etc.
                        .anyRequest().permitAll() // Durante o desenvolvimento, permite todas as rotas para testar
                );

        return http.build();
    }
}