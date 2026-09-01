package com.example.crudusuario.Modules.usuario.Infrastructure.Repositorys;

import com.example.crudusuario.Modules.usuario.Domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);
}
