package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.physician.Physician;
import med.voll.api.physician.PhysicianListData;
import med.voll.api.physician.PhysicianRegisterData;
import med.voll.api.physician.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("medicos")
public class PhysicianController {

    @Autowired
    private PhysicianRepository physicianRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PhysicianRegisterData data) {
        physicianRepository.save(new Physician(data));
    }

    @GetMapping
    public Page<PhysicianListData> physicianList(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return physicianRepository.
                findAll(pageable)
                .map(PhysicianListData::new);
    }

}
