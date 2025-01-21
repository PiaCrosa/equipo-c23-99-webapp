package c23_99_m_webapp.backend.mappers;

import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.models.dtos.ResourceCreateDTO;
import c23_99_m_webapp.backend.models.dtos.ResourceViewDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ResourceCreateMapper {
    public static Resource toEntity(ResourceCreateDTO dto){
        Resource resource = new Resource();

        resource.setName(dto.name());
        resource.setDescription(dto.description());
        resource.setCategory(dto.category());
        resource.setStatus(dto.status());
        resource.setInventoryId(dto.inventoryId());
        return resource;
    }

    public static ResourceCreateDTO toDTO(Resource resource){

        if(resource == null){
            return null;
        }
        return new ResourceCreateDTO(
                resource.getName(),
                resource.getDescription(),
                resource.getCategory(),
                resource.getStatus(),
                resource.getInventoryId()
        );
    }
}

