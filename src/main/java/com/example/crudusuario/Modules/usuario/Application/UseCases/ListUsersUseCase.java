package com.example.crudusuario.Modules.usuario.Application.UseCases;

import com.example.crudusuario.Modules.usuario.Application.DTOs.ResponseUsuario;
import com.example.crudusuario.Modules.usuario.Infrastructure.Repositorys.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUsersUseCase {
    private final UsuarioRepository usuarioRepository;

    public  ListUsersUseCase(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    public List<ResponseUsuario> executeAsync() {
        return usuarioRepository.findAll().stream().map(x -> new ResponseUsuario(
                x.getid(),
                x.getName(),
                x.getEmail(),
                x.getActive()
        )).toList();
    }

}
