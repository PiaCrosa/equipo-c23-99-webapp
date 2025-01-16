package c23_99_m_webapp.backend.models;

import c23_99_m_webapp.backend.models.dtos.ReservationDto;
import c23_99_m_webapp.backend.models.enums.ReservationStatus;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer countElement;
    private LocalDate startDate;
    private LocalDate endDate;
    private String startHour;
    private String endHour;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    private boolean deleted = false;

    public Reservation(){

    }
    public Reservation(ReservationDto reservationDto
                       ) {
        this.countElement = reservationDto.countElement();
        this.startDate = reservationDto.startDate();
        this.endDate = reservationDto.endDate();
        this.startHour = reservationDto.starHour();
        this.endHour = reservationDto.endHour();
    }

    public void handleReservationStatus() {
        switch (this.reservationStatus) {
            case PENDING:
                System.out.println("La reservación está pendiente.");
                break;
            case CANCELLED:
                System.out.println("La reservación ha sido cancelada.");
                break;
            case CONFIRMED:
                System.out.println("La reservación ha sido confirmada.");
                break;
            case REJECTED:
                System.out.println("La reservación ha sido rechazada.");
                break;
            default:
                System.out.println("Estado de reservación desconocido.");
                break;
        }
    }
}