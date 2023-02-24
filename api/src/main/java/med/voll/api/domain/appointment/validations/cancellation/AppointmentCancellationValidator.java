package med.voll.api.domain.appointment.validations.cancellation;

import med.voll.api.domain.appointment.AppointmentDeletionData;

public interface AppointmentCancellationValidator {
    void validate(AppointmentDeletionData data);
}
