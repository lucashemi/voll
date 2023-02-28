package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import med.voll.api.domain.patient.PatientListingData;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {
    @Autowired
    PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientRegistrationData data, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        patientRepository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDetailingData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListingData>> list(@PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pagination) {
        var page = patientRepository.findAllByActiveTrue(pagination).map(PatientListingData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientUpdateData data) {
        Patient patient = patientRepository.getReferenceById(data.id());
        patient.updateInformation(data);

        return ResponseEntity.ok(new PatientDetailingData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);
        var appointments = patientRepository.findAnyAppointmentFromPatient(id);
        if (appointments != null) {
            throw new ValidationException("Can't delete patient with active appointments");
        }
        patient.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id) {
        Patient patient = patientRepository.getReferenceById(id);

        return ResponseEntity.ok(new PatientDetailingData(patient));
    }
}
