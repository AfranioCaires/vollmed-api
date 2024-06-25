package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.*;
import med.voll.api.service.AppointmentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule appointmentSchedule;

    @PostMapping
    @Transactional

    public ResponseEntity<Object> createAppointment (@RequestBody @Valid AppointmentData data, UriComponentsBuilder uriBuilder){
        var dto = appointmentSchedule.schedule(data);
        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid AppointmentCancelData data) {
        appointmentSchedule.cancel(data);
        return ResponseEntity.noContent().build();
    }

}
