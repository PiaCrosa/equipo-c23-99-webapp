package c23_99_m_webapp.backend.controllers;

import c23_99_m_webapp.backend.errors.MyException;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.dtos.DataAnswerUser;
import c23_99_m_webapp.backend.models.dtos.DataUserRegistration;
import c23_99_m_webapp.backend.models.dtos.ReservationDto;
import c23_99_m_webapp.backend.services.ReservationService;
import c23_99_m_webapp.backend.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/reservations")
@SecurityRequirement(name = "bearer-key")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    //ENDPOINT CREATE CON RELACION A USER // crea las relaciones pero aun aparecen nulos los campos tanto en postman como en swagger
    @PostMapping("/create/{dni}")
    public ReservationDto createReservation(@Valid @RequestBody ReservationDto reservationDto, @PathVariable String dni) throws MyException {
        return reservationService.createdReservation(reservationDto,dni);
    }

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


//    @PostMapping("/create") //da error
//    public ResponseEntity<ReservationDto> createReservation(@Valid @RequestBody ReservationDto reservationDto) {
//        try {
//            ReservationDto createdReservation = reservationService.createReservation(reservationDto);
//            return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }