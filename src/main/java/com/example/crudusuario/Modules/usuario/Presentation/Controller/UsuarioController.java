package com.example.crudusuario.Modules.usuario.Presentation.Controller;


import com.example.crudusuario.Modules.usuario.Application.UseCases.CreateUserUseCase;
import com.example.crudusuario.Modules.usuario.Application.UseCases.ListUsersUseCase;
import com.example.crudusuario.Modules.usuario.Presentation.Requests.RequestRegisterUser;
import com.example.crudusuario.Modules.usuario.Application.DTOs.ResponseUsuario;
import com.example.crudusuario.Modules.usuario.UsuarioService;
import com.example.crudusuario.Shared.Web.Response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;

    public  UsuarioController(CreateUserUseCase createUserUseCase, ListUsersUseCase listUsersUseCase){
        this.createUserUseCase = createUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
    }

    @GetMapping("/get")
    public ResponseEntity<SuccessResponse<List<ResponseUsuario>>> listar(){
        var users = listUsersUseCase.executeAsync();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(users));
    }

    @PostMapping("/post")
    public ResponseEntity<SuccessResponse<ResponseUsuario>> criar(@RequestBody RequestRegisterUser request){
        var result = createUserUseCase.executeAsync(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(result));
    }
}
