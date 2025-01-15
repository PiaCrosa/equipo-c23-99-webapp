package c23_99_m_webapp.backend.controllers;

import c23_99_m_webapp.backend.errors.MyException;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.dtos.DataAnswerUser;
import c23_99_m_webapp.backend.models.dtos.DataListUsers;
import c23_99_m_webapp.backend.models.dtos.DataRegistrationUser;
import c23_99_m_webapp.backend.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody DataRegistrationUser dataUserRegistration,
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

    @GetMapping("/getAll")
    public ResponseEntity<Page<DataListUsers>> listUsers(@PageableDefault(size = 3) Pageable pagination) {

        Page<DataListUsers> users = userService.listUsers(pagination);
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody @Valid DataRegistrationUser.DataUpdateUser dataUserUpdate) throws MyException {

        DataListUsers user = userService.updateUser(dataUserUpdate);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/deactivate/{dni}")
    public ResponseEntity deactivateUser(@PathVariable String dni) {
        User user = userService.deactivateUser(dni);
        return ResponseEntity.ok("Usuario desactivado con éxito");
    }

    @PutMapping("/activate/{dni}")
    public ResponseEntity activateUser(@PathVariable String dni) {
        User user = userService.activateUser(dni);
        return ResponseEntity.ok("Usuario activado con éxito");
    }

    @GetMapping("/getDni/{dni}")
    public ResponseEntity<DataListUsers> returnDataUserByDni(@PathVariable String dni) {

        DataListUsers dataUser = userService.returnDataUserByDni(dni);
        return ResponseEntity.ok(dataUser);
    }

    @GetMapping("/getName/{fullName}")
    public ResponseEntity<DataListUsers> returnDataUserByName(@PathVariable String fullName) {

        DataListUsers dataUser = userService.returnDataUserByName(fullName);
        return ResponseEntity.ok(dataUser);
    }
}


