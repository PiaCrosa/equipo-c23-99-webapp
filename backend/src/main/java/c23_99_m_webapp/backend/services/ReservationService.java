package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.exceptions.MyException;
import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.dtos.DataAnswerReservation;
import c23_99_m_webapp.backend.models.dtos.ReservationDto;
import c23_99_m_webapp.backend.models.enums.ReservationStatus;
import c23_99_m_webapp.backend.models.enums.ResourceStatus;
import c23_99_m_webapp.backend.repositories.ReservationRepository;
import c23_99_m_webapp.backend.repositories.ResourceRepository;
import c23_99_m_webapp.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

//  METODO CREATE CON RELACION A USER
    public DataAnswerReservation createdReservation(ReservationDto reservationDto) throws MyException {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByEmail(userDetails.getUsername());

        if (user == null || !user.getActive()) {
            throw new MyException("Usuario no encontrado.");
        }

        Optional<Resource> resourceOptional = resourceRepository.findById(reservationDto.resourceid());
        if (resourceOptional.isEmpty()) {
            throw new MyException("Material no encontrado.");
        }

        Resource resource = resourceOptional.get();

        if (resource.getStatus() == ResourceStatus.IN_USE){
            throw new MyException("El material solicitado está en uso.");

        } else if (resource.getStatus() == ResourceStatus.UNDER_REPAIR) {
            throw  new MyException("El material solicitado se encuentra en reparación.");
        }

        Reservation reservation = new Reservation();
        reservation.setCountElement(reservationDto.countElement());
        reservation.setStartDate(reservationDto.startDate());
        reservation.setReservationShiftStatus(reservationDto.reservationShiftStatus());
        reservation.setSelectedTimeSlot(reservationDto.selectedTimeSlot());
        reservation.setReservationStatus(ReservationStatus.CONFIRMED);
        reservation.setDeleted(false);
        reservation.setUser(user);
        reservation.setResource(resource);

        reservation = reservationRepository.save(reservation);

        DataAnswerReservation data = new DataAnswerReservation(
                reservation.getStartDate(),
                reservation.getReservationShiftStatus(),
                reservation.getSelectedTimeSlot(),
                reservation.getResource().getId(),
                reservation.getReservationStatus()
        );

        return data;
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
                reservation.getReservationShiftStatus(),
                reservation.getSelectedTimeSlot(),
                reservation.getResource().getId()
        );
    }
    public Optional<ReservationDto> updateById (Long id, ReservationDto updatedReservationDto){
        return reservationRepository.findById(id).map(reservation -> {

            reservation.setCountElement(updatedReservationDto.countElement());
            reservation.setStartDate(updatedReservationDto.startDate());
            reservation.setReservationShiftStatus(updatedReservationDto.reservationShiftStatus());
            reservation.setSelectedTimeSlot(updatedReservationDto.selectedTimeSlot());
            reservation.setReservationStatus(ReservationStatus.CONFIRMED);

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

    public Page<LocalDate> findReservationForDate(LocalDate startDate, Pageable pageable) {
        Page<Reservation> reservations = (Page<Reservation>) reservationRepository.findReservationByStartDate(startDate, pageable);
         return reservations.map(Reservation::getStartDate);

    }
}