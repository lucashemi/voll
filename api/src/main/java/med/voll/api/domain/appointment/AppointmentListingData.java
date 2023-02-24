package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentListingData(Long id, LocalDateTime date, Long idDoctor, Long idPatient) {

    public AppointmentListingData(Appointment appointment) {
        this(appointment.getId(), appointment.getDate(), appointment.getDoctor().getId(), appointment.getPatient().getId());
    }
}
