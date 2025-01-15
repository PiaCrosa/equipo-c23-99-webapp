package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.exceptions.MyException;
import c23_99_m_webapp.backend.models.Institution;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.dtos.DataInstitutionRegistration;
import c23_99_m_webapp.backend.models.dtos.DataUserRegistration;
import c23_99_m_webapp.backend.models.enums.Role;
import c23_99_m_webapp.backend.repositories.InstitutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class InstitutionService {

    private final UserService userService;
    private final InstitutionRepository institutionRepository;

    public Institution registerInstitution(DataInstitutionRegistration dataInstitutionRegistration) throws MyException {
        Optional<Institution> optionalInstitution = institutionRepository.findByCue(dataInstitutionRegistration.cue());

        if (optionalInstitution.isPresent()) {
            throw new MyException("La escuela ya está registrada, realiza el login.");
        }

        Institution institution = new Institution(dataInstitutionRegistration);

        institutionRepository.save(institution);

        DataUserRegistration registration = new DataUserRegistration(
                dataInstitutionRegistration.dniAdmin(),
                dataInstitutionRegistration.full_name_admin(),
                dataInstitutionRegistration.email_admin(),
                dataInstitutionRegistration.password_admin(),
                dataInstitutionRegistration.password2_admin(),
                Role.ADMIN,
                institution.getCue()
        );

        User user;
        try {
            user = userService.registerUser(registration);
        } catch (MyException e) {
            throw new MyException("Error al registrar el usuario administrador: " + e.getMessage());
        }

        institution.setUsers(List.of(user));

        return institutionRepository.save(institution);
    }
}
