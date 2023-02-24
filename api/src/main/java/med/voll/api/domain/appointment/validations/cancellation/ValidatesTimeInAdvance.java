package med.voll.api.domain.appointment.validations.cancellation;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentDeletionData;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidatesTimeInAdvanceCancellation")
public class ValidatesTimeInAdvance implements AppointmentCancellationValidator {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentDeletionData data) {
        var appointment = appointmentRepository.getReferenceById(data.idAppointment());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appointment.getDate()).toHours();

        if (differenceInHours < 24) {
            throw new ValidationException("Appointments can only be cancelled with at least 24 hours in advance!");
        }
    }
}
