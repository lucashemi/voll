package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatesActivePatient implements AppointmentSchedulingValidator {

    @Autowired
    private PatientRepository patientRepository;

    public void validate(AppointmentRegistrationData data) {
        var patientIsActive = patientRepository.findActiveById(data.idPatient());
        if (!patientIsActive) {
            throw new ValidationException("Appointment can't be scheduled with an inactive patient");
        }
    }
}
