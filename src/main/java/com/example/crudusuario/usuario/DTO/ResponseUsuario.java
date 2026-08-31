package com.example.crudusuario.usuario.DTO;

import java.util.UUID;

public record ResponseUsuario(UUID id, String nome, String email, boolean active) {
}
