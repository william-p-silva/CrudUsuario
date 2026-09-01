package crudusuario.Modules.usuario.Application.UseCases;

import crudusuario.Modules.usuario.Application.DTOs.ResponseUsuario;
import crudusuario.Modules.usuario.Domain.Entity.Usuario;
import crudusuario.Modules.usuario.Infrastructure.Repositorys.UsuarioRepository;
import crudusuario.Modules.usuario.Infrastructure.Security.PasswordService;
import crudusuario.Modules.usuario.Presentation.Requests.RequestUpdateUser;
import crudusuario.Shared.Exceptions.NotFoundException;
import crudusuario.Shared.Exceptions.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordService passwordService;

    public UpdateUserUseCase(UsuarioRepository usuarioRepository, PasswordService passwordService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordService = passwordService;
    }


    public ResponseUsuario executeAsync(RequestUpdateUser request){
        Usuario user = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado."));

        boolean verifyPassword = passwordService.verifyHash(request.password(), user.getPassword());

        if (!verifyPassword)
            throw new ValidationException("Senha incorreta.");

        request.newName().ifPresent(user::updateName);
        request.newEmail().ifPresent(user::updateEmail);

        return new ResponseUsuario(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getActive()
        );
    }
}

