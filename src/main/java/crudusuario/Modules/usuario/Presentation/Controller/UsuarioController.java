package crudusuario.Modules.usuario.Presentation.Controller;


import crudusuario.Modules.usuario.Application.UseCases.CreateUserUseCase;
import crudusuario.Modules.usuario.Application.UseCases.ListUsersUseCase;
import crudusuario.Modules.usuario.Application.UseCases.UpdateUserUseCase;
import crudusuario.Modules.usuario.Presentation.Requests.RequestRegisterUser;
import crudusuario.Modules.usuario.Application.DTOs.ResponseUsuario;
import crudusuario.Modules.usuario.Presentation.Requests.RequestUpdateUser;
import crudusuario.Shared.Web.Response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    public UsuarioController(
            CreateUserUseCase createUserUseCase,
            ListUsersUseCase listUsersUseCase,
            UpdateUserUseCase updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.updateUserUseCase = updateUserUseCase;
    }

    @GetMapping("/get")
    public ResponseEntity<SuccessResponse<List<ResponseUsuario>>> listar() {
        var users = listUsersUseCase.executeAsync();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(users));
    }

    @PostMapping("/post")
    public ResponseEntity<SuccessResponse<ResponseUsuario>> criar(
            @RequestBody RequestRegisterUser request) {
        var result = createUserUseCase.executeAsync(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(result));
    }

    @PutMapping("/put")
    public ResponseEntity<SuccessResponse<ResponseUsuario>> alterarInfos(
            @RequestBody RequestUpdateUser request) {
        var result = updateUserUseCase.executeAsync(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(result));
    }
}
