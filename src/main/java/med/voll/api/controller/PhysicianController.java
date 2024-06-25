package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.physician.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class PhysicianController {

    @Autowired
    private PhysicianRepository physicianRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PhysicianRegisterData data, UriComponentsBuilder uriBuilder) {

        var physician = new Physician(data);
        physicianRepository.save(physician);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(physician.getId()).toUri();

        return ResponseEntity.created(uri).body(new PhysicianDetailData(physician));
    }

    @GetMapping
    public ResponseEntity<Page<PhysicianListData>> physicianList(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = physicianRepository.
                findAllByActiveTrue(pageable)
                .map(PhysicianListData::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPhysician (@PathVariable Long id){
        var physician = physicianRepository.getReferenceById(id);

        return ResponseEntity.ok(new PhysicianDetailData(physician));
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePhysicianData (@RequestBody @Valid PhysicianUpdateData data){
        var physician = physicianRepository.getReferenceById(data.id());
        physician.updateData(data);

        return ResponseEntity.ok(new PhysicianDetailData(physician));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePhysician (@PathVariable Long id){
        var physician = physicianRepository.getReferenceById(id);
        physician.delete();

        return ResponseEntity.noContent().build();
    }

}

