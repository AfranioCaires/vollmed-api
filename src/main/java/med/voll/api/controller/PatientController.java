package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerPatient (@RequestBody @Valid PatientRegisterData data, UriComponentsBuilder uriBuilder){
        var patient = new Patient(data);
        patientRepository.save(patient);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDetailData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListData>> patientListData(@PageableDefault(size = 10, sort = {"name"})Pageable pageable){
        var patient = patientRepository
                .findAllByActiveTrue(pageable)
                .map(PatientListData::new);

        return ResponseEntity.ok(patient);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPatient (@PathVariable Long id){
        var patient = patientRepository.getReferenceById(id);

        return ResponseEntity.ok(new PatientDetailData(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePatientData (@RequestBody @Valid PatientUpdateData data){
        var patient = patientRepository.getReferenceById(data.id());
        patient.updateData(data);

        return ResponseEntity.ok(new PatientDetailData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient (@PathVariable Long id){
        var patient = patientRepository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }

}
