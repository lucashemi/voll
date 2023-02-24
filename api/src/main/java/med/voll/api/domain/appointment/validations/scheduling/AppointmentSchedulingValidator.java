package med.voll.api.domain.appointment.validations.scheduling;

import med.voll.api.domain.appointment.AppointmentRegistrationData;

public interface AppointmentSchedulingValidator {
    void validate(AppointmentRegistrationData data);
}
