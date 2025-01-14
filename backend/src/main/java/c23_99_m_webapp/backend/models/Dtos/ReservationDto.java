package c23_99_m_webapp.backend.models.dtos;

import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.models.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

    private Long id;
    private Integer countElement;

    //private User user;
    //private Device device;

    private Category reservationStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ReservationDto(Reservation reservation) {
    }
}
