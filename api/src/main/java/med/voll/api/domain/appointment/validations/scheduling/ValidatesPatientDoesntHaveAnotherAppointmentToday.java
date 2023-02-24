package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatesPatientDoesntHaveAnotherAppointmentToday implements AppointmentSchedulingValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentRegistrationData data) {
        var earliestTime = data.date().withHour(7);
        var latestTime = data.date().withHour(18);
        var patientHasAnotherAppointmentToday = appointmentRepository.existsByPatientIdAndDateBetween(data.idPatient(), earliestTime, latestTime);

        if (patientHasAnotherAppointmentToday) {
            throw new ValidationException("Patient already has an scheduled appointment today");
        }
    }
}
