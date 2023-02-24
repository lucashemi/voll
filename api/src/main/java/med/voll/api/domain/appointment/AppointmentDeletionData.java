package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentDeletionData(
        @NotNull
        Long idAppointment,
        @NotNull
        ReasonForCancellation reasonForCancellation
) {
}
