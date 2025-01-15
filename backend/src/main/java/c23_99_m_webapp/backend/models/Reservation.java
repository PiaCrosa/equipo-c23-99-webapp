package c23_99_m_webapp.backend.models;

import c23_99_m_webapp.backend.models.enums.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor

@Data
@Entity
@Table(name="reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonFormat
    private Long id;

    @JsonFormat
    private Integer countElement;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

    @JsonFormat
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    //para borrado logico
//    private boolean deleted = false;
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public Reservation(){

    }

    public Reservation(Integer countElement, LocalDateTime startDate, LocalDateTime endDate, ReservationStatus reservationStatus) {
        this.countElement = countElement;
        this.startDate = startDate;
        this.endDate = endDate;
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
//por problemas con Lombok, los getters y setters estan manuales
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountElement() {
        return countElement;
    }

    public void setCountElement(Integer countElement) {
        this.countElement = countElement;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

//    public boolean isDeleted() {
//        return deleted;
//    }
//
//    public void setDeleted(boolean deleted) {
//        this.deleted = deleted;
//    }
}
//private User user; // establecer relación, ya que el usuario (docente) va a realizar las reservas
//private Device device;