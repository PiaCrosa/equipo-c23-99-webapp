package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.errors.ResourceNotFoundException;
import c23_99_m_webapp.backend.mappers.ResourceMapper;
import c23_99_m_webapp.backend.models.dtos.ResourceDTO;
import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.repositories.ResourceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static c23_99_m_webapp.backend.mappers.ResourceMapper.toDTO;
import static c23_99_m_webapp.backend.mappers.ResourceMapper.toEntity;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository){
        this.resourceRepository = resourceRepository;

    }

    public List<ResourceDTO> getResources() {
        List<Resource> resources = resourceRepository.findAll();
        return resources.stream().map(ResourceMapper::toDTO).collect(Collectors.toList());

    }

    public ResourceDTO saveResource(ResourceDTO resourceDTO){
        Resource resource = resourceRepository.save(toEntity(resourceDTO));
        return toDTO(resource);

    }

    public ResourceDTO updateResource(long id, ResourceDTO dto) {
        Resource existingResource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el recurso con Id: " + id));

        existingResource.setName(dto.name());
        existingResource.setDescription(dto.description());
        existingResource.setCategory(dto.category());
        existingResource.setAvailable(dto.available());

        Resource modifiedResource = resourceRepository.save(existingResource);

        return toDTO(modifiedResource);
    }

    public void deleteResource(Long id){
        Resource resource = resourceRepository.findByIdAndAvailable(id, true)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el recurso con Id: " + id));
        resource.setAvailable(false); //TODO: cambiar por activo
        resourceRepository.save(resource);

    }





}
