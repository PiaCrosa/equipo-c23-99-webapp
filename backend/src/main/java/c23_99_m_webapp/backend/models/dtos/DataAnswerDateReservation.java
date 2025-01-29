package c23_99_m_webapp.backend.models.dtos;

import c23_99_m_webapp.backend.models.enums.ReservationStatus;

import java.time.LocalDate;

public record DataAnswerDateReservation(
        LocalDate startDate,
        ReservationStatus reservationStatus,
        String username,
        String name

) {

}
