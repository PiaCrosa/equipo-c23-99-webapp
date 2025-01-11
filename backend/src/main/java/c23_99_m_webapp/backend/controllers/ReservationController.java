package c23_99_m_webapp.backend.controllers;

import c23_99_m_webapp.backend.models.Dtos.ReservationDto;
import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = reservationService.createReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @GetMapping("/allreservations")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.findAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/getreservation/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        return reservation.map(res -> new ResponseEntity<>(res, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PutMapping("/updateReservation/{id}")
//    public ResponseEntity<ReservationDto> updateReservationById(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
//        ReservationDto updatedReservation = reservationService.updateReservation(id, reservationDto);
//        if (updatedReservation != null) {
//            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
