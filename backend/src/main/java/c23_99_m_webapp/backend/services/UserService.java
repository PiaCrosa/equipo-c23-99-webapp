package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.errors.MyException;
import c23_99_m_webapp.backend.models.Institution;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.dtos.DataUserRegistration;
import c23_99_m_webapp.backend.repositories.InstitutionRepository;
import c23_99_m_webapp.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;

    public User registerUser(DataUserRegistration dataUserRegistration) throws MyException {
        Institution institutionEncontrada = null;
        System.out.println(dataUserRegistration.institution_cue());
        try {
            valida(dataUserRegistration.password(), dataUserRegistration.password2());

            if (dataUserRegistration.institution_cue() != null) {
                institutionEncontrada = institutionRepository
                        .findByCue(dataUserRegistration.institution_cue())
                        .orElseThrow(() -> new MyException("Institución no encontrada con el CUE proporcionado."));
            }

            User user = new User(dataUserRegistration, institutionEncontrada);
            return userRepository.save(user);

        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public void valida(String password, String password2) throws MyException {
        if (!password.equals(password2)) {
            throw new MyException("Las contraseñas deben coincidir.");
        }
    }
}
