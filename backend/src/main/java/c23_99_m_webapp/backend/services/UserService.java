package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.exceptions.MyException;
import c23_99_m_webapp.backend.models.Institution;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.dtos.DataListUsers;
import c23_99_m_webapp.backend.models.dtos.DataRegistrationUser;
import c23_99_m_webapp.backend.models.enums.Role;
import c23_99_m_webapp.backend.repositories.InstitutionRepository;
import c23_99_m_webapp.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final InstitutionRepository institutionRepository;
    private final EmailService emailService;

    @Transactional
    public User registerUser(DataRegistrationUser dataUserRegistration) throws MyException {
        Institution institutionEncontrada = null;
        System.out.println(dataUserRegistration.institution_cue());
        try {
            validPassword(dataUserRegistration.password(), dataUserRegistration.password2());

            if (dataUserRegistration.institution_cue() != null) {
                institutionEncontrada = institutionRepository
                        .findByCue(dataUserRegistration.institution_cue())
                        .orElseThrow(() -> new MyException("Institución no encontrada con el CUE proporcionado."));
            }
            validateEmail(dataUserRegistration.email());

            User user = new User(dataUserRegistration, institutionEncontrada);

            userRepository.save(user);
            if (institutionEncontrada != null & user.getRole().equals(Role.TEACHER)) {
                emailService.getEmailTeacher(
                        user.getEmail(),
                        user.getFullName(),
                        dataUserRegistration.password(),
                        institutionEncontrada.getName()
                );
            }
            return user;

        } catch (MyException e) {
            throw e;
        } catch (Exception e) {
            throw new MyException("Error al guardar el usuario: " + e.getMessage());
        }
    }

    public void validPassword(String password, String password2) throws MyException {
        if (!password.equals(password2)) {
            throw new MyException("Las contraseñas deben coincidir.");
        }
    }

    public void validateEmail(String email) throws MyException {

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!email.matches(emailRegex)) {
            throw new MyException("El formato del email no es válido.");
        }
        if (userRepository.existsByEmail(email)) {
            throw new MyException("El email ya está registrado.");
        }
    }

    public Page<DataListUsers> listUsers(Pageable pagination) {
        return userRepository.findByActiveTrue(pagination).map(DataListUsers::new);
    }
    @Transactional
    public DataListUsers updateUser(DataRegistrationUser.DataUpdateUser dataUserUpdate) throws MyException {

        User user = userRepository.findByDni(dataUserUpdate.dni())
                .orElseThrow(() -> new MyException("Usuario no encontrado con el DNI proporcionado."));

        validPassword(dataUserUpdate.password(), dataUserUpdate.password2());

        if (!Objects.equals(dataUserUpdate.email(), user.getEmail())) {
            if (userRepository.existsByEmail(dataUserUpdate.email())) {
                throw new MyException("El email ya está registrado.");
            }
            validateEmail(dataUserUpdate.email());
        }

        if (!Objects.equals(dataUserUpdate.email(), user.getEmail())) {
            emailService.getEmailTeacherUpdate(
                    user.getEmail(),
                    user.getFullName(),
                    user.getPassword(),
                    user.getInstitution().getName()
            );
        }

        user.updateData(dataUserUpdate);
        userRepository.save(user);

        return new DataListUsers(
                user.getDni(),
                user.getFullName(),
                user.getEmail(),
                user.getInstitution().getName()
        );
    }

    @Transactional
    public void deactivateUser(String dni) {
        User user = userRepository.getReferenceByDni(dni);
        user.deactivateUser();
    }

    @Transactional
    public void activateUser(String dni) {
        User user = userRepository.getReferenceByDni(dni);
        user.activateUser();
    }

    public DataListUsers returnDataUserByDni(String dni) {
        User user = userRepository.getReferenceByDni(dni);
        return new DataListUsers(user.getDni(),user.getFullName(),user.getEmail(),user.getInstitution().getCue());
    }

    public DataListUsers returnDataUserByName(String fullName) {
        User user = userRepository.getReferenceByFullName(fullName);
        return new DataListUsers(user.getDni(),user.getFullName(),user.getEmail(),user.getInstitution().getCue());
    }

    public void deleteUser(String dni) {
        User user = userRepository.getReferenceByDni(dni);
        userRepository.delete(user);
    }

    public User getCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername());
    }
}
