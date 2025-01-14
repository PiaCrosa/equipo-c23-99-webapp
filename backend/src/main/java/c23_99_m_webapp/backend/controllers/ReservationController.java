package c23_99_m_webapp.backend.controllers;

import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<Reservation> addreRervation(@RequestBody Reservation reservation){
       return ResponseEntity.ok().body(reservationService.createReservation(reservation).getBody());
    }

    @GetMapping("/allreservations")
    public ResponseEntity<List<Reservation>> getAllReservations(){
        List<Reservation> reservationList = reservationService.getReservations();
        return ResponseEntity.ok().body(reservationList);
    }

    @GetMapping("/reservation/{id}")
    public ResponseEntity<Optional<Reservation>> getReservationById(@PathVariable Long id){
        return ResponseEntity.ok().body(reservationService.findReservationById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<Reservation>> updateReservationbyId(@PathVariable Long id, @RequestBody Reservation reservation) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.updateById(id,reservation));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReservation(@PathVariable Long id){
       reservationService.deleteReservationOnly(id);
    }

//    @DeleteMapping("/delete/{id}")
//    public void deleteReservation(@PathVariable Long id){
//        reservationService.deleteReservationById(id);
//    }

}
