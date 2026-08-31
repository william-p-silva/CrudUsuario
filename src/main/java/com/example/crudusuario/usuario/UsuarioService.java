package com.example.crudusuario.usuario;

import com.example.crudusuario.usuario.DTO.RequestRegisterUser;
import com.example.crudusuario.usuario.DTO.ResponseUsuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listar() {
        return repository.findAll();
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
