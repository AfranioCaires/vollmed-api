package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.physician.Physician;
import med.voll.api.physician.PhysicianRegisterData;
import med.voll.api.physician.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class PhysicianController {

    @Autowired
    private PhysicianRepository physicianRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PhysicianRegisterData data){
        physicianRepository.save(new Physician(data));
    }
}

