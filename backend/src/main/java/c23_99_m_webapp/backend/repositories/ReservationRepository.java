package c23_99_m_webapp.backend.repositories;


import c23_99_m_webapp.backend.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
