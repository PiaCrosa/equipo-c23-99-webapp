package c23_99_m_webapp.backend.models.dtos;


import c23_99_m_webapp.backend.models.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataUserRegistration(

        @NotBlank
        String dni,
        @NotBlank
        String full_name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String password2,
        Role role,
        String institution_cue
) {
}
