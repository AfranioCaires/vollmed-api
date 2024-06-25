package med.voll.api.service;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.*;
import med.voll.api.domain.appointment.validations.cancellation.AppointmentCancellationValidator;
import med.voll.api.domain.appointment.validations.scheduling.AppointmentValidator;
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

    @Autowired
    private List<AppointmentCancellationValidator> appointmentCancellationValidators;

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

    public void cancel(AppointmentCancelData data) {
        if (!appointmentRepository.existsById(data.appointmentId())) {
            throw new ValidationException("Id da consulta informado não existe!");
        }

        appointmentCancellationValidators.forEach(v -> v.validate(data));

        var appointment = appointmentRepository.getReferenceById(data.appointmentId());
        appointment.cancel(data.reason());
    }
}
