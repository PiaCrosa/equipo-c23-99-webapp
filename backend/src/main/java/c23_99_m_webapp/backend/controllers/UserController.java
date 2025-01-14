package c23_99_m_webapp.backend.controllers;

import c23_99_m_webapp.backend.errors.MyException;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.dtos.DataAnswerUser;
import c23_99_m_webapp.backend.models.dtos.DataUserRegistration;
import c23_99_m_webapp.backend.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody DataUserRegistration dataUserRegistration,
                                          UriComponentsBuilder uriComponentsBuilder) {
        try {
            User user = userService.registerUser(dataUserRegistration);
            DataAnswerUser dataAnswerUser = new DataAnswerUser(
                    user.getFullName(),
                    user.getEmail()
            );
            URI url = uriComponentsBuilder.path("/user/{dni}").buildAndExpand(user.getDni()).toUri();
            return ResponseEntity.created(url).body(dataAnswerUser);
        } catch (MyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }
}

