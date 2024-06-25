package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceTimeValidator implements AppointmentValidator {
    public void validate(AppointmentData data){
        var appointmentDate = data.date();
        var now = LocalDateTime.now();
        var minutesDifference = Duration.between(now, appointmentDate).toMinutes();

        if(minutesDifference < 30){
            throw new ValidationException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
