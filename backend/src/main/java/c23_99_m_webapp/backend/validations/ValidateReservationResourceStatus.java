package c23_99_m_webapp.backend.validations;

import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.models.dtos.ReservationDto;
import c23_99_m_webapp.backend.repositories.ResourceRepository;
import c23_99_m_webapp.backend.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateReservationResourceStatus {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ResourceService resourceService;

    public Resource validateByResourceStatus(ReservationDto reservationDto){

        Resource resource = resourceRepository.findById(reservationDto.resourceid()).orElseThrow();
        resourceService.validateResourceAvailability(resource.getId());

        return resource;
    }
}
