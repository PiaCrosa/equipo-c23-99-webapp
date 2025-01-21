package c23_99_m_webapp.backend.repositories;

import c23_99_m_webapp.backend.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
