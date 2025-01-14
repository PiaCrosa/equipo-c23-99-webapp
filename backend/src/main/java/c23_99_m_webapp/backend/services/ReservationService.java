package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public ResponseEntity<Reservation> createReservation(Reservation reservation) {
        reservationRepository.save(reservation);
        return ResponseEntity.ok().build();
    }

    //usar Dto para este metodo
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    //usar Dto para este metodo
    public Optional<Reservation> findReservationById(Long id) {
        return ResponseEntity.ok(reservationRepository.findById(id)).getBody();
    }


    public Optional<Reservation> updateById(Long id, Reservation updatedReservation) {
        return reservationRepository.findById(id).map(reservation ->{
            reservation.setCountElement(updatedReservation.getCountElement());
            reservation.setStartDate(updatedReservation.getStartDate());
            reservation.setEndDate(updatedReservation.getEndDate());
            reservation.setReservationStatus(updatedReservation.getReservationStatus());
            return reservationRepository.save(reservation);
        });
    }
    public void deleteReservationOnly(Long id){
        reservationRepository.findById(id)
                .ifPresent(reservationRepository::delete);
    }

    //metodo borrado logico
//    public void deleteReservationById(Long id) {
//        Reservation reservation = reservationRepository.findById(id).get();
//        reservation.setDeleted(true);
//        reservationRepository.save(reservation);
//    }

//    public List<Reservation> getDeletedReservations() {
//        return reservationRepository.findAllDeleted();
//    }

//    public void restoreReservation(Long id){
//        Reservation reservation = reservationRepository.findById(id).orElse(null):
//        assert reservation != null;
//        if (reservation.isDeleted()){
//            reservation.setDeleted(false);
//            reservationRepository.save(reservation);
//        }
//    }
}
