package com.example.crudusuario.Modules.usuario.Application.UseCases;

import com.example.crudusuario.Modules.usuario.Infrastructure.Security.PasswordService;
import com.example.crudusuario.Modules.usuario.Presentation.Requests.RequestRegisterUser;
import com.example.crudusuario.Modules.usuario.Application.DTOs.ResponseUsuario;
import com.example.crudusuario.Modules.usuario.Domain.Entity.Usuario;
import com.example.crudusuario.Modules.usuario.Infrastructure.Repositorys.UsuarioRepository;
import org.springframework.stereotype.Service;


@Service
public class CreateUserUseCase {
    private final UsuarioRepository usuarioRepository;
    private final PasswordService passwordService;

    public CreateUserUseCase(UsuarioRepository usuarioRepository, PasswordService passwordService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordService = passwordService;
    }

    public ResponseUsuario executeAsync(RequestRegisterUser request) {

        var userExist = usuarioRepository.findByEmail(request.email());
        if (userExist.isPresent())
            throw new IllegalArgumentException("Usuário já existe.");

        var hashPassword = passwordService.hashPassword(request.password());

        var user = new Usuario(
                request.nome(),
                request.email(),
                hashPassword
        );

        var savedUser = usuarioRepository.save(user);

        return new ResponseUsuario(
                savedUser.getid(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getActive()
        );
    }
}
