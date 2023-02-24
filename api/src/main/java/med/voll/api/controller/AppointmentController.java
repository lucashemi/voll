package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {
    @Autowired
    private AppointmentsSchedule appointmentsSchedule;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid AppointmentRegistrationData data, UriComponentsBuilder uriBuilder) {
        var dto = appointmentsSchedule.schedule(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid AppointmentDeletionData data) {
        appointmentsSchedule.cancel(data);
        return ResponseEntity.noContent().build();
    }


}
