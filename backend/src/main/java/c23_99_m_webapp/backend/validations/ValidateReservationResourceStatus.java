package c23_99_m_webapp.backend.validations;

import c23_99_m_webapp.backend.exceptions.MyException;
import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.models.dtos.ReservationDto;
import c23_99_m_webapp.backend.models.enums.ResourceStatus;
import c23_99_m_webapp.backend.repositories.ReservationRepository;

import c23_99_m_webapp.backend.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidateReservationResourceStatus {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ResourceRepository resourceRepository;

    public Resource validateByResourceStatus(ReservationDto reservationDto) throws MyException {

        Optional<Resource> resourceOptional = resourceRepository.findById(reservationDto.resourceid());

        if (resourceOptional.isEmpty()) {
            throw new MyException("No se encuentra el material solicitado");
        }

        Resource resource = resourceOptional.get();

        if (resource.getStatus() == ResourceStatus.IN_USE) {
            throw new MyException("El material solicitado está en uso.");

        } else if (resource.getStatus() == ResourceStatus.UNDER_REPAIR) {
            throw new MyException("El material solicitado se encuentra en reparación.");
        }
        return resource;
    }
}
