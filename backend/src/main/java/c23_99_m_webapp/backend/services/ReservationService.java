package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.models.Dtos.ReservationDto;
import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

//    public ReservationDto updateReservation(Long id, ReservationDto reservationDto) {
//        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
//        if (optionalReservation.isPresent()) {
//            Reservation reservation = optionalReservation.get();
//            reservation.setCountElement(reservationDto.getCountElement());
//            reservation.setReservationStatus(reservationDto.getReservationStatus());
//            reservation.setStartDate(reservationDto.getStartDate());
//            reservation.setEndDate(reservationDto.getEndDate());
//
//            Reservation updatedReservation = reservationRepository.save(reservation);
//            return new ReservationDto(updatedReservation);
//        }
//        return null;
//    }

    public List<ReservationDto> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDto> reservationDtoList = reservations.stream().map(ReservationDto::new).collect(Collectors.toList());
        return reservationDtoList;
    }
}
