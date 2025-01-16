package c23_99_m_webapp.backend.models.dtos;


import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.enums.ReservationStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public record ReservationDto (

        @NotNull
        Integer countElement,
        @NotNull
        LocalDate startDate,
        @NotNull
        LocalDate endDate,
        @NotNull
        String starHour,
        @NotNull
        String endHour,
        ReservationStatus reservationStatus,
        User user,
        Resource resource
){

}
