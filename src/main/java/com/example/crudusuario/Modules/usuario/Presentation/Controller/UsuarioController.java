package com.example.crudusuario.Modules.usuario.Presentation.Controller;


import com.example.crudusuario.Modules.usuario.Application.UseCases.CreateUserUseCase;
import com.example.crudusuario.Modules.usuario.Application.UseCases.ListUsersUseCase;
import com.example.crudusuario.Modules.usuario.Presentation.Requests.RequestRegisterUser;
import com.example.crudusuario.Modules.usuario.Application.DTOs.ResponseUsuario;
import com.example.crudusuario.Modules.usuario.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;

    public  UsuarioController(UsuarioService usuarioService, CreateUserUseCase createUserUseCase, ListUsersUseCase listUsersUseCase){
        this.createUserUseCase = createUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
    }

    @GetMapping("/get")
    public List<ResponseUsuario> listar(){
        return listUsersUseCase.executeAsync();
    }

    @PostMapping("/post")
    public ResponseUsuario criar(@RequestBody RequestRegisterUser request){
        return createUserUseCase.executeAsync(request);
    }
}
