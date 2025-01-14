package c23_99_m_webapp.backend.repositories;


import c23_99_m_webapp.backend.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

//    @Query("SELECT r FROM Reservations p WHERE r.deleted = true")
//    List<Reservation> findAllDeleted();
}
