package med.voll.api.domain.appointment.validations.cancellation;


import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentCancelData;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceTimeCancellingValidator implements AppointmentCancellationValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentCancelData data) {
        var appointment = appointmentRepository.getReferenceById(data.appointmentId());
        var now = LocalDateTime.now();
        var hoursDifference = Duration.between(now, appointment.getDate()).toHours();

        if (hoursDifference < 24) {
            throw new ValidationException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
