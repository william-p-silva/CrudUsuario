package crudusuario.Modules.usuario.Presentation.Controller;

import crudusuario.Modules.usuario.Application.UseCases.*;
import crudusuario.Modules.usuario.Presentation.Requests.RequestRegisterUser;
import crudusuario.Modules.usuario.Application.DTOs.ResponseUsuario;
import crudusuario.Modules.usuario.Presentation.Requests.RequestUpdateUser;
import crudusuario.Shared.Web.Response.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@Tag(
        name = "Usuários",
        description = "Endpoints para gerenciamento de usuários"
)
public class UsuarioController {

    private final CreateUserUseCase createUserUseCase;
    private final ListUsersUseCase listUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeletarUserUseCase deletarUserUseCase;
    private final BuscarUserUseCase buscarUserUseCase;

    public UsuarioController(
            CreateUserUseCase createUserUseCase,
            ListUsersUseCase listUsersUseCase,
            UpdateUserUseCase updateUserUseCase, DeletarUserUseCase deletarUserUseCase, BuscarUserUseCase buscarUserUseCase) {

        this.createUserUseCase = createUserUseCase;
        this.listUsersUseCase = listUsersUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deletarUserUseCase = deletarUserUseCase;
        this.buscarUserUseCase = buscarUserUseCase;
    }

    @Operation(
            summary = "Lista usuários",
            description = "Retorna todos os usuários cadastrados."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuários listados com sucesso"
            )
    })
    @GetMapping("/get")
    public ResponseEntity<SuccessResponse<List<ResponseUsuario>>> listar() {

        var users = listUsersUseCase.executeAsync();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(users));
    }

    @Operation(
            summary = "Buscar um usuário em especifico",
            description = "Exibe as informações do usuario"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuário encontrado"
    )
    @GetMapping("/get/id")
    public ResponseEntity<SuccessResponse<ResponseUsuario>> buscar(@RequestParam UUID id){
        var user = buscarUserUseCase.executeAsync(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(user));
    }


    @Operation(
            summary = "Cria um usuário",
            description = "Cadastra um novo usuário."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário criado com sucesso"
            )
    })
    @PostMapping("/post")
    public ResponseEntity<SuccessResponse<ResponseUsuario>> criar(
            @RequestBody RequestRegisterUser request) {

        var result = createUserUseCase.executeAsync(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(result));
    }


    @Operation(
            summary = "Atualiza um usuário",
            description = "Atualiza as informações de um usuário existente."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário atualizado com sucesso"
            )
    })
    @PutMapping("/put")
    public ResponseEntity<SuccessResponse<ResponseUsuario>> alterarInfos(
            @RequestBody RequestUpdateUser request) {

        var result = updateUserUseCase.executeAsync(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(result));
    }

    @Operation(
            summary = "Deletar Usuário",
            description = "Deletar um usuário pelo seu ID."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Usuário deletado"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<SuccessResponse<String>> deletar(@RequestParam UUID id){

        var result = deletarUserUseCase.executeAsync(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(result));
    }
}