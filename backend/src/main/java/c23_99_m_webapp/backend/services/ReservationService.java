package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.errors.MyException;
import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.dtos.DataUserRegistration;
import c23_99_m_webapp.backend.models.dtos.ReservationDto;
import c23_99_m_webapp.backend.models.enums.ReservationStatus;
import c23_99_m_webapp.backend.repositories.ReservationRepository;
import c23_99_m_webapp.backend.repositories.ResourceRepository;
import c23_99_m_webapp.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ResourceRepository resourceRepository;

//   METODO CREATE CON RELACION A USER
    public ReservationDto createdReservation(ReservationDto reservationDto) throws MyException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());

        if (user == null || !user.getActive()) {
            throw new MyException("User  not found.");
        }

        Optional<Resource> resource = resourceRepository.findById(reservationDto.resourceid());

        if (resource.isEmpty()) {
            throw new MyException("Resource not found");
        }
            Reservation reservation = new Reservation();
            reservation.setCountElement(reservationDto.countElement());
            reservation.setStartDate(reservationDto.startDate());
            reservation.setStartHour(reservationDto.starHour());
            reservation.setEndHour(reservationDto.endHour());
            reservation.setReservationStatus(ReservationStatus.CONFIRMED);
            reservation.setUser(user);
            reservation.setResource(resource.get());

            reservation = reservationRepository.save(reservation);

            return convertToDto(reservation);
    }

    public List<ReservationDto> getReservations () {
        return reservationRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<ReservationDto> findReservationById (Long id){
        return reservationRepository.findById(id)
                .map(this::convertToDto);
    }

    private ReservationDto convertToDto (Reservation reservation){

        return new ReservationDto(
                reservation.getCountElement(),
                reservation.getStartDate(),
                reservation.getStartHour(),
                reservation.getEndHour(),
                reservation.getId()
        );
    }
    public Optional<ReservationDto> updateById (Long id, ReservationDto updatedReservationDto){
        return reservationRepository.findById(id).map(reservation -> {

            reservation.setCountElement(updatedReservationDto.countElement());
            reservation.setStartDate(updatedReservationDto.startDate());
            reservation.setStartHour(updatedReservationDto.starHour());
            reservation.setEndHour(updatedReservationDto.endHour());
            reservation.setReservationStatus(ReservationStatus.valueOf(""));

            Reservation updatedReservation = reservationRepository.save(reservation);
            return convertToDto(updatedReservation);
        });
    }

    public void deleteReservationById (Long id){
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setDeleted(true);
        reservationRepository.save(reservation);
    }

    public List<ReservationDto> getDeletedReservations () {
        List<Reservation> deletedReservations = reservationRepository.findAllDeleted();
        return deletedReservations.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void restoreReservation (Long id){
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        assert reservation != null;
        if (reservation.isDeleted()) {
            reservation.setDeleted(false);
            reservationRepository.save(reservation);
        }
    }
}