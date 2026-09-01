package com.example.crudusuario.Modules.usuario.Infrastructure.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    public String hashPassword(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (password.trim().isEmpty())
            throw new IllegalArgumentException("A senha não pode ser vazia.");
        if(password.length() <= 6)
            throw new IllegalArgumentException("A senha é muito curta.");

        return encoder.encode(password);
    }

    public boolean verifyHash(String password, String hash){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(password, hash);
    }
}
