package com.example.crudusuario.usuario;


import com.example.crudusuario.usuario.DTO.RequestRegisterUser;
import com.example.crudusuario.usuario.DTO.ResponseUsuario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final  UsuarioService usuarioService;

    public  UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/get")
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    @PostMapping("/post")
    public ResponseUsuario criar(@RequestBody RequestRegisterUser request){
        return usuarioService.create(request);
    }
}
