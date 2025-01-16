package c23_99_m_webapp.backend.services;

import c23_99_m_webapp.backend.errors.MyException;
import c23_99_m_webapp.backend.models.Reservation;
import c23_99_m_webapp.backend.models.Resource;
import c23_99_m_webapp.backend.models.User;
import c23_99_m_webapp.backend.models.dtos.ReservationDto;
import c23_99_m_webapp.backend.repositories.ReservationRepository;
import c23_99_m_webapp.backend.repositories.ResourceRepository;
import c23_99_m_webapp.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResourceRepository resourceRepository;

//    METODO CREATE CON RELACION A USER - resource QUE FUNCIONA
public ReservationDto createdReservation(ReservationDto reservationDto, String dni) throws MyException {
    User user = reservationRepository.findByDni(dni);
    //Optional<Resource> resource = resourceRepository.findById(id);
    if (user == null || !user.getActive()) {
        throw new MyException("User  not found.");
    }
//    if (resource.isEmpty()){
//        throw new MyException("Resource not found");
//    }
    Reservation reservation = new Reservation();
    reservation.setCountElement(reservationDto.countElement());
    reservation.setStartDate(reservationDto.startDate());
    reservation.setEndDate(reservationDto.endDate());
    reservation.setStartHour(reservationDto.starHour());
    reservation.setEndHour(reservationDto.endHour());
    reservation.setReservationStatus(reservationDto.reservationStatus());
    reservation.setUser(reservationDto.user());
    reservation.setResource(reservationDto.resource());
    reservation = reservationRepository.save(reservation);

    return convertToDto(reservation);
}

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
        return new ReservationDto(
                reservation.getCountElement(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStartHour(),
                reservation.getEndHour(),
                reservation.getReservationStatus(),
                reservation.getUser(),
                reservation.getResource()
        );
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


//public ReservationDto createReservation(ReservationDto reservationDto) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User user = userRepository.findByEmail(userDetails.getUsername());
//        Long resource = reservationDto.resource().getId();
//
//        final var reservation = getReservation(reservationDto);
//
//        reservationRepository.save(reservation);
//        return reservationDto;
//    }
//    private static Reservation getReservation(ReservationDto reservationDto) {
//        Reservation reservation = new Reservation();
//        reservation.setCountElement(reservationDto.countElement());
//        reservation.setStartDate(reservationDto.startDate());
//        reservation.setEndDate(reservationDto.endDate());
//        reservation.setStartHour(reservationDto.starHour());
//        reservation.setEndHour(reservationDto.endHour());
//        reservation.setReservationStatus(reservationDto.reservationStatus());
//        reservation.setUser(reservationDto.user());
//        reservation.setResource(reservationDto.resource());
//        return reservation;
//    }
