package com.example.crudusuario.Modules.usuario;

import com.example.crudusuario.Modules.usuario.Presentation.Requests.RequestRegisterUser;
import com.example.crudusuario.Modules.usuario.Application.DTOs.ResponseUsuario;
import com.example.crudusuario.Modules.usuario.Domain.Entity.Usuario;
import com.example.crudusuario.Modules.usuario.Infrastructure.Repositorys.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<ResponseUsuario> listar() {
        return repository.findAll().stream().map(x -> new ResponseUsuario(
                x.getid(),
                x.getName(),
                x.getEmail(),
                x.getActive()
        )).toList();
    }

    public ResponseUsuario create(RequestRegisterUser request) {
        var user = new Usuario(
                request.nome(),
                request.email(),
                request.password()
        );

        var savedUser = repository.save(user);

        return new ResponseUsuario(
                savedUser.getid(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getActive()
        );
    }

}
