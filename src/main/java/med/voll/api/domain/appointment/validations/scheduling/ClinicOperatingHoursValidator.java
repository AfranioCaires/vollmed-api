package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ClinicOperatingHoursValidator implements AppointmentValidator {
    public void validate(AppointmentData data){
       var appointmentDate = data.date();
       var isDateSunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);

       var isBeforeClinicOpeningTime = appointmentDate.getHour() < 7;
       var isAfterClinicOpeningTime = appointmentDate.getHour() > 18;

       if (isDateSunday|| isBeforeClinicOpeningTime || isAfterClinicOpeningTime){
           throw new ValidationException("Consulta fora do horário de funcionamento da clínica");
       }

    }
}
