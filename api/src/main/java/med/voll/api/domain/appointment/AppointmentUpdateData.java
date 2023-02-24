package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentUpdateData(
        @NotNull
        Long id,
        Integer year,
        Integer month,
        Integer day,
        Integer hour,
        Integer minute,
        String doctor,
        String patient
) {
}
