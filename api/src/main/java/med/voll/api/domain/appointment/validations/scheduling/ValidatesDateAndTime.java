package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidatesDateAndTime implements AppointmentSchedulingValidator {
    public void validate(AppointmentRegistrationData data) {
        var appointmentDate = data.date();
        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeTheClinicOpens = appointmentDate.getHour() < 7;
        var afterTheClinicCloses = appointmentDate.getHour() > 18;

        if (sunday || beforeTheClinicOpens || afterTheClinicCloses) {
            throw new ValidationException("Appointment outside opening hours or date");
        }
    }
}
