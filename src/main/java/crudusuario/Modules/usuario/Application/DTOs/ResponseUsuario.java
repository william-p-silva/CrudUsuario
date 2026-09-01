package crudusuario.Modules.usuario.Application.DTOs;

import java.util.UUID;

public record ResponseUsuario(UUID id, String nome, String email, boolean active) {
}
