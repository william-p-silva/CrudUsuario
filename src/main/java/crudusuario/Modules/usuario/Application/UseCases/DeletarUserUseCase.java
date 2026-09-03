package crudusuario.Modules.usuario.Application.UseCases;

import crudusuario.Modules.usuario.Domain.Entity.Usuario;
import crudusuario.Modules.usuario.Infrastructure.Repositorys.UsuarioRepository;
import crudusuario.Shared.Exceptions.ValidationException;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class DeletarUserUseCase {
    private final UsuarioRepository usuarioRepository;

    public DeletarUserUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public String executeAsync(UUID id){
        if (id == null )
            throw new ValidationException("ID invalido");

        Usuario user = usuarioRepository.findAllById(id)
                .orElseThrow(() -> new ValidationException("Usuário não encontrado"));

        usuarioRepository.delete(user);

        return "Usuário deletado.";
    }
}
