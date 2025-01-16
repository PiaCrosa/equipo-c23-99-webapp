package c23_99_m_webapp.backend.controllers;

import c23_99_m_webapp.backend.errors.MyException;
import c23_99_m_webapp.backend.models.dtos.DataAnswerReservation;
import c23_99_m_webapp.backend.models.dtos.DataAnswerUser;
import c23_99_m_webapp.backend.models.dtos.DataUserRegistration;
import c23_99_m_webapp.backend.models.dtos.ReservationDto;
import c23_99_m_webapp.backend.models.enums.ReservationStatus;
import c23_99_m_webapp.backend.repositories.UserRepository;
import c23_99_m_webapp.backend.security.DataJWTtoken;
import c23_99_m_webapp.backend.services.ReservationService;
import c23_99_m_webapp.backend.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/reservations")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

    //ENDPOINT CREATE CON RELACION A USER
    @PostMapping("/create")
    public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationDto reservationDto) throws MyException {

        try {
            ReservationDto reservationDto1 = reservationService.createdReservation(reservationDto);
            //DEBERIA mostrar el nombre del user que hizo la reserva, la fecha de la reserva, el recurso seleccionado y el estado
            DataAnswerReservation dataAnswerReservation = new DataAnswerReservation(reservationDto1.startDate(),reservationDto1.starHour(),
                    reservationDto1.resourceid(),
                    ReservationStatus.valueOf(""));

            return ResponseEntity.ok(Map.of("status",
                    "success", "message",
                    "Reserva creada con éxito","data",
                    dataAnswerReservation));

        } catch (MyException e) {
            return ResponseEntity.badRequest().body(Map.of("status",
                    "error", "message", e.getMessage()));
        } catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status",
                    "error", "message",
                    "Error al crear la reserva."));
        }
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