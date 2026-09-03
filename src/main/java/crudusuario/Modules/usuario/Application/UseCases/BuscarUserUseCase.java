package crudusuario.Modules.usuario.Application.UseCases;

import crudusuario.Modules.usuario.Application.DTOs.ResponseUsuario;
import crudusuario.Modules.usuario.Domain.Entity.Usuario;
import crudusuario.Modules.usuario.Infrastructure.Repositorys.UsuarioRepository;
import crudusuario.Shared.Exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BuscarUserUseCase {
    private final UsuarioRepository usuarioRepository;

    public BuscarUserUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseUsuario executeAsync(UUID id){
        Usuario user = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        return new ResponseUsuario(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getActive()
        );
    }
}
