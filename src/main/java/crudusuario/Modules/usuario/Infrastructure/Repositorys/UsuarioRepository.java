package crudusuario.Modules.usuario.Infrastructure.Repositorys;

import crudusuario.Modules.usuario.Domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findAllById(UUID id);

    void delete(Usuario user);
}
