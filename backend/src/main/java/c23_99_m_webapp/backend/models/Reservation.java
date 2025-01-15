package c23_99_m_webapp.backend.models;

import c23_99_m_webapp.backend.models.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@Data
@Entity
@Table(name="reservations")
public class Reservation {
//agregar hora de inicio y hora fin
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer countElement;

//    @ManyToOne
//    @JoinColumn(name = "users")
//    private User user;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startHour;
    private LocalTime endHour;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private boolean deleted = false;

    public Reservation(){

    }

    public Reservation(Integer countElement, LocalDate startDate, LocalDate endDate,LocalTime startHour, LocalTime endHour, ReservationStatus reservationStatus) {
        this.countElement = countElement;
//        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startHour = startHour;
        this.endHour = endHour;
        this.reservationStatus = reservationStatus;
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

    //    public void setUser(User user) {
//        this.user = user;
//    }
}
//relacion con inventario
//private Device device;