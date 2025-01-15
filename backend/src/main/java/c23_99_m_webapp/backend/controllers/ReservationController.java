package c23_99_m_webapp.backend.controllers;

import c23_99_m_webapp.backend.errors.MyException;
import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.models.dtos.ReservationDto;
import c23_99_m_webapp.backend.services.ReservationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/reservations")
@SecurityRequirement(name = "bearer-key")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDto reservationDto){
       return ResponseEntity.ok().body(reservationService.createReservation(reservationDto));
    }

    //ENDPOINT CREATE CON RELACION A USER
//    @PostMapping("/create/{dni}")
//    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDto, @PathVariable String dni) throws MyException {
//        return reservationService.createReservation(reservationDto, dni);
//    }

    @GetMapping("/allreservations")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservationList = reservationService.getReservations();
        return ResponseEntity.ok().body(reservationList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ReservationDto>> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok().body(reservationService.findReservationById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Optional<ReservationDto>> updateById(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.updateById(id, reservationDto));
    }

    @PutMapping("/delete/{id}") //borrado logico
    public void deleteReservation(@PathVariable Long id){
        reservationService.deleteReservationById(id);
    }

    @GetMapping("/alldeleted")
    public List<ReservationDto> deleted(){
        return reservationService.getDeletedReservations();
    }

    @PutMapping("/restore/{id}")
    public void restore(@PathVariable Long id){
        reservationService.restoreReservation(id);
    }
}
