package med.voll.api.service;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.appointment.AppointmentDetailData;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.validations.AppointmentValidator;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.physician.Physician;
import med.voll.api.domain.physician.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentValidator> validatorList;

    public AppointmentDetailData schedule(AppointmentData data) {

        if (!patientRepository.existsById(data.idPatient())) {
            throw new ValidationException("ID do paciente não encontrado");
        }

        if (data.idPhysician() != null && !physicianRepository.existsById(data.idPhysician())) {
            throw new ValidationException("ID do médico não encontrado");
        }

        validatorList.forEach(v -> v.validate(data));

        var patient = patientRepository.findById(data.idPatient());
        var physician = selectPhysician(data);

        if (physician == null){
            throw new ValidationException("Não existe médico disponível nessa data");
        }

        var appointment = Appointment.builder()
                .physician(physician)
                .patient(patient.get())
                .date(data.date())
                .build();

        appointmentRepository.save(appointment);

        return new AppointmentDetailData(appointment);
    }

    private Physician selectPhysician(AppointmentData data) {
        if (data.idPhysician() != null) {
            return physicianRepository.getReferenceById(data.idPhysician());
        }

        if (data.specialty() == null){
            throw new ValidationException("Especialidade obrigatória quando médico não for escolhido");
        }

        return physicianRepository.choosePhysicianRandomly(data.specialty(), data.date());
    }
}
