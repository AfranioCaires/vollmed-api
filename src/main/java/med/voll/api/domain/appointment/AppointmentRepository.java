package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByPhysicianIdAndDate(Long idPhysician, LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long patientId, LocalDateTime firstTimeSlot, LocalDateTime secondTimeSlot);


}
