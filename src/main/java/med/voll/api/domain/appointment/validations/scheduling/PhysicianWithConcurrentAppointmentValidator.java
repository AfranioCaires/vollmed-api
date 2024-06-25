package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhysicianWithConcurrentAppointmentValidator implements AppointmentValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentData data) {
        var isPhysicianNotAvailable = appointmentRepository.existsByPhysicianIdAndDateAndCancellationReasonIsNull(data.idPhysician(), data.date());

        if (isPhysicianNotAvailable) {
            throw new ValidationException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }

}
