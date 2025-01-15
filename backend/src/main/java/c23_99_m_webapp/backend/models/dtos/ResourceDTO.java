package c23_99_m_webapp.backend.models.dtos;

import c23_99_m_webapp.backend.models.enums.ResourceCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResourceDTO (

        Long id,
        @NotBlank
        String name,
        @NotBlank
        String description,
        ResourceCategory category,
        @NotNull
        Boolean available
    ){
}

