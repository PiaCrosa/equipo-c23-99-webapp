package c23_99_m_webapp.backend.controllers;

import c23_99_m_webapp.backend.errors.MyException;
import c23_99_m_webapp.backend.models.Institution;
import c23_99_m_webapp.backend.models.dtos.*;
import c23_99_m_webapp.backend.services.InstitutionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping("/institution")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-key")
public class InstitutionController {

    private final InstitutionService institutionService;

    @PostMapping("/register")
    public ResponseEntity<?> registerInstitution(
            @Valid @RequestBody DataRegistrationInstitution dataInstitutionRegistration,
            UriComponentsBuilder uriComponentsBuilder) {
        try {
            Institution institution = institutionService.registerInstitution(dataInstitutionRegistration);
            DataAnswerInstitution dataAnswerInstitution = new DataAnswerInstitution(
                    institution.getName(),
                    institution.getAddress(),
                    institution.getEmail(),
                    institution.getPhone()
            );
            URI url = uriComponentsBuilder.path("/institution/{cue}").buildAndExpand(institution.getCue()).toUri();
            return ResponseEntity.created(url).body(dataAnswerInstitution);
        } catch (MyException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateInstitution(@RequestBody @Valid DataRegistrationInstitution.DataUpdateInstitution dataUpdateInstitution) throws MyException {

        DataListInstitution institution = institutionService.updateInstitution(dataUpdateInstitution);
        return ResponseEntity.ok(institution);
    }
}
