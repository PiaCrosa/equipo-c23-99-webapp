package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.exceptions.BadCustomerRequestException;
import c23_99_m_webapp.backend.exceptions.ResourceNotFoundException;
import c23_99_m_webapp.backend.exceptions.ResourceUnavailableException;
import c23_99_m_webapp.backend.mappers.ResourceCreateMapper;
import c23_99_m_webapp.backend.mappers.ResourceViewMapper;
import c23_99_m_webapp.backend.models.dtos.ResourceCreateDTO;
import c23_99_m_webapp.backend.models.dtos.ResourceViewDTO;
import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.models.enums.ResourceStatus;
import c23_99_m_webapp.backend.repositories.ReservationRepository;
import c23_99_m_webapp.backend.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static c23_99_m_webapp.backend.mappers.ResourceCreateMapper.toEntity;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;
    private final ReservationRepository reservationRepository;

    public ResourceService(ResourceRepository resourceRepository, ReservationRepository reservationRepository){
        this.resourceRepository = resourceRepository;
        this.reservationRepository = reservationRepository;

    }

    public List<ResourceViewDTO> getResources() {
        List<Resource> resources = resourceRepository.findAll();
        return resources.stream().map(ResourceViewMapper::toDTO).collect(Collectors.toList());

    }

    public ResourceViewDTO getResourceById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el recurso con Id: " + id));

        return ResourceViewMapper.toDTO(resource);
    }
//
//    public ResourceViewDTO saveResource(ResourceCreateDTO resourceDTO){
//        Resource resource = resourceRepository.save(toEntity(resourceDTO));
//        return ResourceViewMapper.toDTO(resource);
//
//    }

    public Resource saveResourceEntity(ResourceCreateDTO resourceDTO) {
        Resource resource = toEntity(resourceDTO);
        return resourceRepository.save(resource);
    }


    public ResourceCreateDTO setInventoryId(ResourceCreateDTO dto, Long inventoryId){
        return new ResourceCreateDTO(dto.name(), dto.description(), dto.category(),
                dto.status(), inventoryId);

    }

    public ResourceViewDTO updateResource(long id, ResourceCreateDTO dto) {
        Resource existingResource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el recurso con Id: " + id));

        existingResource.setName(dto.name());
        existingResource.setDescription(dto.description());
        existingResource.setCategory(dto.category());
        existingResource.setStatus(dto.status());

        Resource modifiedResource = resourceRepository.save(existingResource);

        return ResourceViewMapper.toDTO(modifiedResource);
    }

    @Transactional
    public void deleteResource(long id) throws BadCustomerRequestException{
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro al recurso con id: " + id));

        boolean existingResourceReserve = this.reservationRepository.existsByResource(resource);

        if(existingResourceReserve){
            throw new BadCustomerRequestException("No es posible eliminar un recurso con reservas asociadas");
        }
        this.resourceRepository.deleteById(id);
    }


    public ResourceViewDTO updateAndReturnResourceStatus(Long id, ResourceStatus status){
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el recurso con Id: " + id));

        resource.setStatus(status);
        resourceRepository.save(resource);

        return ResourceViewMapper.toDTO(resource);

    }

    public List<ResourceViewDTO> getResourcesByStatus(ResourceStatus status){
        List<Resource> resourcesByStatus = resourceRepository.findAllByStatus(status);
        return resourcesByStatus.stream().map(ResourceViewMapper::toDTO).collect(Collectors.toList());
    }

    public void validateResourceAvailability(Long resourceId) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso no encontrado con ID: " + resourceId));

        if (!resource.getStatus().equals(ResourceStatus.AVAILABLE)) {
            throw new ResourceUnavailableException("El recurso no está disponible para reservar.");
        }
    }

    public void updateResourceStatus(Long resourceId, ResourceStatus status) {
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso no encontrado con ID: " + resourceId));
        resource.setStatus(status);
        resourceRepository.save(resource);
    }


}
