package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.exceptions.MyException;
import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.dtos.DataAnswerReservation;
import c23_99_m_webapp.backend.models.dtos.ReservationDto;
import c23_99_m_webapp.backend.models.enums.ReservationShiftStatus;
import c23_99_m_webapp.backend.models.enums.ReservationStatus;
import c23_99_m_webapp.backend.repositories.ReservationRepository;
import c23_99_m_webapp.backend.repositories.ResourceRepository;
import c23_99_m_webapp.backend.validations.ValidateReservationResourceStatus;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    UserService userService;

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    ValidateReservationResourceStatus validateReservationResourceStatus;

    public DataAnswerReservation createdReservation(ReservationDto reservationDto) throws MyException {
        User user = userService.getCurrentUser();
        Resource resource = validateReservationResourceStatus.validateByResourceStatus(reservationDto);
//        Optional<Resource> resourceOptional = resourceRepository.findById(reservationDto.resourceid());
//
//        if (resourceOptional.isEmpty()) {
//            throw new MyException("No se encuentra el material solicitado");
//        }
//
//        Resource resource = resourceOptional.get();
//
//        if (resource.getStatus() == ResourceStatus.IN_USE){
//            throw new MyException("El material solicitado está en uso.");
//
//        } else if (resource.getStatus() == ResourceStatus.UNDER_REPAIR) {
//            throw  new MyException("El material solicitado se encuentra en reparación.");
//        }

        Reservation reservation = new Reservation();
        reservation.setStartDate(reservationDto.startDate());
        reservation.setReservationShiftStatus(reservationDto.reservationShiftStatus());
        reservation.setSelectedTimeSlot(reservationDto.selectedTimeSlot());
        reservation.setReservationStatus(ReservationStatus.CONFIRMED);
        reservation.setDeleted(false);
        reservation.setUser(user);
        reservation.setResource(resource);

        reservation = reservationRepository.save(reservation);

        DataAnswerReservation data = new DataAnswerReservation(
                reservation.getUser().getEmail(),
                reservation.getStartDate(),
                reservation.getReservationShiftStatus(),
                reservation.getSelectedTimeSlot(),
                reservation.getResource().getName(),
                reservation.getReservationStatus()
        );
        return data;
    }

    public List<ReservationDto> getReservations() throws MyException {
        try {
            return reservationRepository.findAll().stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new MyException("Error al obtener lista.");
        }
    }

    public Optional<ReservationDto> findReservationById(Long id) throws MyException {
        if (id == null) {
            throw new MyException("El id no puede ser nulo.");
        }
        return reservationRepository.findById(id)
                .map(this::convertToDto);
    }

    private ReservationDto convertToDto(Reservation reservation){

        return new ReservationDto(
                reservation.getStartDate(),
                reservation.getReservationShiftStatus(),
                reservation.getSelectedTimeSlot(),
                reservation.getResource().getId()
        );
    }

    public Optional<ReservationDto> updateById(Long id, ReservationDto updatedReservationDto) throws MyException {

        if (id == null){
            throw new MyException("No se encuentra la reserva para actualizar");
        }
        return reservationRepository.findById(id).map(reservation -> {
            reservation.setStartDate(updatedReservationDto.startDate());
            reservation.setReservationShiftStatus(updatedReservationDto.reservationShiftStatus());
            reservation.setSelectedTimeSlot(updatedReservationDto.selectedTimeSlot());
            reservation.setReservationStatus(ReservationStatus.CONFIRMED);

            Reservation updatedReservation = reservationRepository.save(reservation);
            return convertToDto(updatedReservation);
        });
    }

    public void deleteReservationById(Long id) throws MyException {

        if (!reservationRepository.existsById(id)) {
            throw new MyException("No se encuentra la reserva.");
        }
        Reservation reservation = reservationRepository.findById(id).get();
        reservation.setDeleted(true);
        reservationRepository.save(reservation);
    }

    public List<ReservationDto> getDeletedReservations() throws MyException {
        try {
            List<Reservation> deletedReservations = reservationRepository.findAllDeleted();
            return deletedReservations.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());

        } catch (RuntimeException e) {
            throw new MyException("Error al traer la lista de reservas borradas.");
        }
    }

    public void restoreReservation(Long id) throws MyException {
        try {
            Reservation reservation = reservationRepository.findById(id).orElse(null);
            assert reservation != null;

            if (reservation.isDeleted()) {
                reservation.setDeleted(false);
                reservationRepository.save(reservation);
            }

        } catch (RuntimeException e) {
            throw new MyException("Error al restaurar la reserva.");
        }
    }

    public Page<DataAnswerReservation> findByDate(LocalDate startDate, Pageable pageable) throws MyException{
        try {
            Page<Reservation> reservationPage = reservationRepository.findReservationByDate(startDate, pageable);
            return reservationPage.map(reservation -> {
                DataAnswerReservation data = new DataAnswerReservation(
                        reservation.getUser().getEmail(),
                        reservation.getStartDate(),
                        reservation.getReservationShiftStatus(),
                        reservation.getSelectedTimeSlot(),
                        reservation.getResource().getName(),
                        reservation.getReservationStatus()
                );
                return data;
            });
        } catch (RuntimeException e) {
            throw new MyException(e.getMessage());
        }
    }

    public Page<DataAnswerReservation> findByStatus(ReservationStatus status, Pageable pageable) throws MyException{

        try {
            Page<Reservation> reservations = reservationRepository.findReservationByStatus(status, pageable);
            return reservations.map(reservation -> {
                DataAnswerReservation dateReservation = new DataAnswerReservation(
                        reservation.getUser().getEmail(),
                        reservation.getStartDate(),
                        reservation.getReservationShiftStatus(),
                        reservation.getSelectedTimeSlot(),
                        reservation.getResource().getName(),
                        reservation.getReservationStatus()
                );
                return dateReservation;
            });
        } catch (Exception e) {
            throw new MyException("No se pudieron recuperar las reservas.");
        }
    }

    public Page<DataAnswerReservation> findByShiftStatus(ReservationShiftStatus reservationShiftStatus, Pageable pageable) throws MyException {
        //realizar validacion de turno (si hay 2 horarios iguales con el mismo recurso)
        try {
            Page<Reservation> reservations = reservationRepository.findReservationByShiftStatus(reservationShiftStatus, pageable);
            return reservations.map(reservation -> {

                DataAnswerReservation dataAnswerReservation = new DataAnswerReservation(
                        reservation.getUser().getEmail(),
                        reservation.getStartDate(),
                        reservation.getReservationShiftStatus(),
                        reservation.getSelectedTimeSlot(),
                        reservation.getResource().getName(),
                        reservation.getReservationStatus()
                );
                return dataAnswerReservation;
            });
        } catch (Exception e) {
            throw new MyException("No se pudieron recuperar las reservas.");
        }
    }

    public Page<DataAnswerReservation> findByUserDni(String user, Pageable pageable) throws MyException {

        try {
            Page<Reservation> reservationByUserDni = reservationRepository.findReservationByUserDni(user, pageable);
            return reservationByUserDni.map(reservation -> {

                DataAnswerReservation dataAnswerReservation = new DataAnswerReservation(
                        reservation.getUser().getEmail(),
                        reservation.getStartDate(),
                        reservation.getReservationShiftStatus(),
                        reservation.getSelectedTimeSlot(),
                        reservation.getResource().getName(),
                        reservation.getReservationStatus()
                );
                return dataAnswerReservation;
            });
        } catch (Exception e) {
            throw new MyException("No se pudo realizar la búsqueda.");
        }
    }
}