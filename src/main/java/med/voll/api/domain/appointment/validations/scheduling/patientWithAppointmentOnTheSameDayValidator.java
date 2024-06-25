package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class patientWithAppointmentOnTheSameDayValidator implements AppointmentValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentData data){
        var firstTimeSlot = data.date().withHour(7);
        var secondTimeSlot = data.date().withHour(18);

        var isPatientWithAppointmentOnTheSameDay = appointmentRepository
                .existsByPatientIdAndDateBetween(data.idPatient(), firstTimeSlot, secondTimeSlot);

        if (isPatientWithAppointmentOnTheSameDay){
            throw new ValidationException("Paciente já possui uma consulta agendada nesse dia.");
        }
    }
}
