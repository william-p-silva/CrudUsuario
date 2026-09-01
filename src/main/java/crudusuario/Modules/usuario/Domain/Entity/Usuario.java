package crudusuario.Modules.usuario.Domain.Entity;

import crudusuario.Shared.Exceptions.ValidationException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue
    private UUID id;

    private String email;
    private String name;
    private String password;
    private Boolean active;

    protected Usuario() { }

    public Usuario(
            String name,
            String email,
            String password
    ){
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Nome não pode ser vazio");
        if (email == null || email.trim().isEmpty())
            throw new IllegalArgumentException("Nome não pode ser vazio");
        if (password == null || password.trim().isEmpty())
            throw new IllegalArgumentException("Nome não pode ser vazio");

        this.email = email;
        this.name = name;
        this.password = password;
        this.active = true;
    }

    public String getName(){
        return this.name;
    }

    public UUID getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public boolean getActive(){
        return this.active;
    }


    public void updateName(String newName){
        if (newName == null || newName.isBlank())
            throw new ValidationException("O novo nome não pode ser vazio.");

        this.name = newName;
    }

    public void updateEmail(String newEmail){
        if (newEmail == null || newEmail.isBlank())
            throw new ValidationException("O novo email não pode ser vazio.");
    }
}
