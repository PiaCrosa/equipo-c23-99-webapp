package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.dtos.ReservationDto;
import c23_99_m_webapp.backend.repositories.ReservationRepository;
import c23_99_m_webapp.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;

    public ReservationDto createReservation(ReservationDto reservationDto) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());

        Reservation reservation = new Reservation();
        reservation.setCountElement(reservationDto.countElement());
        reservation.setStartDate(reservationDto.startDate());
        reservation.setEndDate(reservationDto.endDate());
        reservation.setStartHour(reservationDto.starHour());
        reservation.setEndHour(reservationDto.endHour());
        reservation.setReservationStatus(reservationDto.reservationStatus());
        reservation.setUser(reservationDto.user());

        reservationRepository.save(reservation);
        return reservationDto;
    }

//    //METODO CREATE CON RELACION A USER
//    public Reservation createReservation(ReservationDto reservationDto, String userDni) throws MyException {
//
//        Optional<User> userOptional = userRepository.findById(userDni);
//        if (userOptional.isEmpty()) {
//            throw new MyException("User not found.");
//        }
//        // Crear la reserva a partir del DTO
//        Reservation reservation = new Reservation();
//        reservation.setCountElement(reservationDto.countElement());
//        reservation.setUser (String.valueOf(userOptional.get()));
//        reservation.setStartDate(reservationDto.startDate());
//        reservation.setEndDate(reservationDto.endDate());
//        reservation.setStartHour(updatedReservationDto.starHour());
//        reservation.setEndHour(updatedReservationDto.endHour());
//        reservation.setReservationStatus(reservationDto.reservationStatus());
//
//        // Guardar la reserva en la base de datos
//        reservationRepository.save(reservation);
//        return reservation;
//    }

    public List<ReservationDto> getReservations() {
        return reservationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ReservationDto> findReservationById(Long id) {
        return reservationRepository.findById(id)
                .map(this::convertToDto);
    }

    private ReservationDto convertToDto(Reservation reservation) {
        return new ReservationDto(reservation.getCountElement(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStartHour(),
                reservation.getEndHour(),
                reservation.getReservationStatus(),
                reservation.getUser());
    }

    public Optional<ReservationDto> updateById(Long id, ReservationDto updatedReservationDto) {

        return reservationRepository.findById(id).map(reservation -> {

            reservation.setCountElement(updatedReservationDto.countElement());
            reservation.setStartDate(updatedReservationDto.startDate());
            reservation.setEndDate(updatedReservationDto.endDate());
            reservation.setStartHour(updatedReservationDto.starHour());
            reservation.setEndHour(updatedReservationDto.endHour());
            reservation.setReservationStatus(updatedReservationDto.reservationStatus());

            Reservation updatedReservation = reservationRepository.save(reservation);
            return convertToDto(updatedReservation);
        });
    }

    public void deleteReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setDeleted(true);
        reservationRepository.save(reservation);
    }

    public List<ReservationDto> getDeletedReservations() {
        List<Reservation> deletedReservations = reservationRepository.findAllDeleted();
        return deletedReservations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void restoreReservation(Long id){
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        assert reservation != null;
        if (reservation.isDeleted()){
            reservation.setDeleted(false);
            reservationRepository.save(reservation);
        }
    }
}
