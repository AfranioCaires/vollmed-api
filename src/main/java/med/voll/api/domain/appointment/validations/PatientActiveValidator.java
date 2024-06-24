package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientActiveValidator implements AppointmentValidator {

    @Autowired
    private PatientRepository patientRepository;

    public void validate(AppointmentData data){
        var isPatientActive = patientRepository.findActiveById(data.idPatient());

        if (!isPatientActive){
            throw new ValidationException("Consulta não deve ser agendada com paciente excluído");
        }
    }
}
