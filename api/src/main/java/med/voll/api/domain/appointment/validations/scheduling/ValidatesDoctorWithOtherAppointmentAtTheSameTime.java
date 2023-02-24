package med.voll.api.domain.appointment.validations.scheduling;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatesDoctorWithOtherAppointmentAtTheSameTime implements AppointmentSchedulingValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(AppointmentRegistrationData data) {
        var doctorHasAnotherAppointmentAtTheSameTime = appointmentRepository.existsByDoctorIdAndDateAndActiveIsTrue(data.idDoctor(), data.date());
        if (doctorHasAnotherAppointmentAtTheSameTime) {
            throw new ValidationException("This doctor already has an appointment at this time and date");
        }
    }
}
