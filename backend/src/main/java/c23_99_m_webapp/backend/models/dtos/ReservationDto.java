package c23_99_m_webapp.backend.models.dtos;

import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.enums.ReservationStatus;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDto (

        @NotNull
        Integer countElement,
//        @NotNull
//        User user,
        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate endDate,

        @NotNull
        LocalTime starHour,

        @NotNull
        LocalTime endHour,

        @NotNull
        ReservationStatus reservationStatus
){

}
