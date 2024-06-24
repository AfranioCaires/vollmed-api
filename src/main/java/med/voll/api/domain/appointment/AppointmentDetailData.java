package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailData(Long id, Long idPhysician, Long idPatient, LocalDateTime date) {
    public AppointmentDetailData(Appointment appointment) {
        this(appointment.getId(), appointment.getPhysician().getId(), appointment.getPatient().getId(),appointment.getDate());
    }
}
