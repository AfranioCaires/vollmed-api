package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void registerPatient (@RequestBody @Valid PatientRegisterData data){
        patientRepository.save(new Patient(data));
    }

    @GetMapping
    public Page<PatientListData> patientListData(@PageableDefault(size = 10, sort = {"name"})Pageable pageable){
        return patientRepository
                .findAllByActiveTrue(pageable)
                .map(PatientListData::new);
    }

    @PutMapping
    @Transactional
    public void updatePatientData (@RequestBody @Valid PatientUpdateData data){
        var patient = patientRepository.getReferenceById(data.id());
        patient.updateData(data);
    }

}
