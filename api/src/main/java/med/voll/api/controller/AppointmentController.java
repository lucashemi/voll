package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.*;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorDetailingData;
import med.voll.api.domain.doctor.DoctorListingData;
import med.voll.api.domain.doctor.DoctorUpdateData;
import med.voll.api.domain.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.print.Doc;

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

    @GetMapping
    public ResponseEntity<Page<AppointmentDetailingData>> list(@PageableDefault(sort = {"date"}, direction = Sort.Direction.ASC) Pageable pagination) {
        var page = appointmentsSchedule.list(pagination);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid AppointmentUpdateData data) throws Exception {
        Appointment appointment = appointmentsSchedule.getReferenceById(data.id());
        Patient patient = appointmentsSchedule.getPatient(data.idPatient());
        Doctor doctor = appointmentsSchedule.getDoctor(data);
        appointment.updateInformation(data, doctor, patient);

        return ResponseEntity.ok(new AppointmentListingData(appointment));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid AppointmentDeletionData data) {
        appointmentsSchedule.cancel(data);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        Appointment appointment = appointmentsSchedule.getReferenceById(id);

        return ResponseEntity.ok(new AppointmentListingData(appointment));
    }


}
