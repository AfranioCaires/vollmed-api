package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.physician.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhysicianActiveValidator implements AppointmentValidator {

    @Autowired
    private PhysicianRepository physicianRepository;

    public void validate(AppointmentData data){
        if (data.idPhysician() == null){
            return;
        }

        var isPhysicianActive = physicianRepository.findActiveById(data.idPhysician());

        if (!isPhysicianActive){
            throw new ValidationException("Consulta não deve ser agendada com médico excluído");
        }
    }
}
