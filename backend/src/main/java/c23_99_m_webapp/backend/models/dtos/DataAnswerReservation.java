package c23_99_m_webapp.backend.models.dtos;

import c23_99_m_webapp.backend.models.enums.ReservationStatus;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;

//lo que se deberia mostrar en consola (respuesta)
public record DataAnswerReservation(

        //User user,
        LocalDate Date,
        String hour,
        Long resourceid,
        ReservationStatus reservationStatus

) {
}
