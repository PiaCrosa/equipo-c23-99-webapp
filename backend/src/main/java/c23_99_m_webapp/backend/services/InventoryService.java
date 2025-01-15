package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.mappers.ResourceMapper;
import c23_99_m_webapp.backend.models.Inventory;
import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.models.dtos.InventoryDTO;
import c23_99_m_webapp.backend.models.dtos.ResourceDTO;
import c23_99_m_webapp.backend.repositories.InventoryRepository;
import c23_99_m_webapp.backend.repositories.ResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService( InventoryRepository inventoryRepository){
        this.inventoryRepository = inventoryRepository;

    }

//    @Override
//    public InventoryDTO getInventoryByInstitutionId(long id) {
//        Optional<Inventory> inventory = inventoryRepository.findById(id);
//        if(empleado.isPresent()){
//            return toDTO(empleado.get());
//        } else{
//            throw new ResourceNotFoundException("No se encontro el empleado con Id: " + id);
//        }
//
//    }

//    public List<InventoryDTO> getInventories() {
//        List<Inventory> inventories = inventoryRepository.findAll();
//        return inventories.stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
//
//    }


}
