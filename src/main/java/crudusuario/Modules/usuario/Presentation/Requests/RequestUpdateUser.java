package crudusuario.Modules.usuario.Presentation.Requests;

import java.util.Optional;

public record RequestUpdateUser(
        String email,
        String password,
        Optional<String> newName,
        Optional<String> newEmail
        ) {
}
