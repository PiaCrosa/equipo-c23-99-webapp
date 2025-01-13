package c23_99_m_webapp.backend.models;

import c23_99_m_webapp.backend.models.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer countElement;

    //private User user;
    //private Device device;

    private Category reservationStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
