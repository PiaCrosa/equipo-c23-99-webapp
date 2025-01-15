package c23_99_m_webapp.backend.models.Dtos;

import c23_99_m_webapp.backend.models.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReservationDto {

    private Long id;
    private Integer countElement;

    //private User user;
    //private Device device;

    private ReservationStatus reservationStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ReservationDto() {
    }
    public ReservationDto(Integer countElement, ReservationStatus reservationStatus, LocalDateTime startDate, LocalDateTime endDate) {
        this.countElement = countElement;
        this.reservationStatus = reservationStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
