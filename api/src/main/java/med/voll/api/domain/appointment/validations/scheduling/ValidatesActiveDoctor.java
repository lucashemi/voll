package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatesActiveDoctor implements AppointmentSchedulingValidator {

    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(AppointmentRegistrationData data) {
        // random doctor
        if (data.idDoctor() == null) {
            return;
        }

        var doctorIsActive = doctorRepository.findActiveById(data.idDoctor());
        if (!doctorIsActive) {
            throw new ValidationException("Appointment can't be scheduled with an inactive doctor");
        }
    }
}
