package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Specialty;

import java.time.LocalDateTime;

public record AppointmentUpdateData(
        @NotNull
        Long id,
        LocalDateTime date,
        Long idDoctor,
        Long idPatient,
        Specialty specialty
) {
}
