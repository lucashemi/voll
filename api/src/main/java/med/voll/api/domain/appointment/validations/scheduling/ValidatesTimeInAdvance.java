package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidatesTimeInAdvanceScheduling")
public class ValidatesTimeInAdvance implements AppointmentSchedulingValidator {
    public void validate(AppointmentRegistrationData data) {
        var appointmentDate = data.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ValidationException("Appointment should be scheduled half an hour in advance");
        }
    }
}
