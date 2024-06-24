package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.appointment.AppointmentData;
import med.voll.api.domain.appointment.AppointmentDetailData;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.service.AppointmentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("consultas")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule appointmentSchedule;

    @PostMapping
    @Transactional

    public ResponseEntity<Object> createAppointment (@RequestBody @Valid AppointmentData data, UriComponentsBuilder uriBuilder){
        var dto = appointmentSchedule.schedule(data);

        var appointment = new Appointment(data);

        var uri = uriBuilder.path("/consultas/{id}").buildAndExpand(appointment.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
