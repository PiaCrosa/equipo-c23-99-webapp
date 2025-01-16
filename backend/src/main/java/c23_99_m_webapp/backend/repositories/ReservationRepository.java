package c23_99_m_webapp.backend.repositories;


import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query("SELECT r FROM Reservation r WHERE r.deleted = true")
    List<Reservation> findAllDeleted();

    @Query("SELECT u FROM User u WHERE u.dni = :dni")
    User findByDni(@Param("dni") String dni);

    @Query("SELECT r FROM Resource r WHERE r.id = :id")
    Resource findResourceById(@Param("id") Long id);

    @Query("SELECT r FROM Resource r")
    List<Resource> findAllResources();


}
